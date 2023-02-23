package com.lfx.demo.rocketmq.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lfx.demo.rocketmq.constant.AppConstant;
import com.lfx.demo.rocketmq.enums.TopicEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.slf4j.MDC;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:idler41@163.con">linfuxin</a> created on 2023-02-14 11:44:16
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = "${rocketmq.consumer.topic:}", consumerGroup = "${rocketmq.consumer.group:}")
public class NativeDispatchConsumer implements RocketMQListener<MessageExt>, RocketMQPushConsumerLifecycleListener, MessageListenerConcurrently,
        InitializingBean, ApplicationContextAware {

    @Value("${rocketmq.consumer.consumeThreadMax:20}")
    private Integer consumeThreadMax;

    @Value("${rocketmq.consumer.consumeThreadMin:20}")
    private Integer consumeThreadMin;

    @Autowired
    private ObjectMapper objectMapper;
    private ApplicationContext applicationContext;

    private Map<TopicEnum, NativeMessageHandler<? super Serializable>> handlerMap;

    @Autowired
    private Validator validator;

    @Override
    public void onMessage(MessageExt messageExt) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> messageExtList, ConsumeConcurrentlyContext context) {
        for (MessageExt messageExt : messageExtList) {
            try {
                long now = System.currentTimeMillis();
                ConsumeConcurrentlyStatus consumeStatus = doConsumeMessage(messageExt, context);
                if (log.isDebugEnabled()) {
                    log.debug("consume {} cost: {} ms", messageExt.getMsgId(), System.currentTimeMillis() - now);
                }
                if (consumeStatus == ConsumeConcurrentlyStatus.RECONSUME_LATER) {
                    return consumeStatus;
                }
            } catch (Exception e) {
                log.warn("consume message failed. reConsumeTimes: {}", messageExt.getReconsumeTimes(), e);
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {
        // 参数化改造
        consumer.setConsumeThreadMax(consumeThreadMax);
        consumer.setConsumeThreadMin(consumeThreadMin);
        // 这里改为rocketmq原生监听器
        consumer.setMessageListener(this);
        if (log.isDebugEnabled()) {
            log.debug("修改mq消费者线程数!");
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //noinspection rawtypes
        Map<String, NativeMessageHandler> handlerBeanMap = applicationContext.getBeansOfType(NativeMessageHandler.class);
        if (handlerBeanMap.isEmpty()) {
            handlerMap = Collections.emptyMap();
            log.warn("rocketmq消息处理器为空!");
            return;
        }
        //noinspection unchecked
        handlerMap = handlerBeanMap.values().stream().collect(Collectors.toMap(NativeMessageHandler::getTopic, v -> v));
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    private ConsumeConcurrentlyStatus doConsumeMessage(MessageExt messageExt, ConsumeConcurrentlyContext context) throws IOException {
        // todo 这里改为分布式id
        MDC.put(AppConstant.TRACE_ID_KEY, String.valueOf(System.currentTimeMillis()));
        String body = new String(messageExt.getBody());
        if (log.isDebugEnabled()) {
            log.debug("received message,topic={},tags={},msgId={},times={},body={}",
                    messageExt.getTopic(), messageExt.getTags(), messageExt.getMsgId(), messageExt.getReconsumeTimes(), body);
        } else {
            log.info("received message,msgId={},times={}", messageExt.getMsgId(), messageExt.getReconsumeTimes());
        }
        TopicEnum topicEnum = TopicEnum.getInstance(messageExt.getTopic(), messageExt.getTags());
        if (topicEnum == null) {
            log.warn("no message handler match!body={}", body);
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }
        NativeMessageHandler<? super Serializable> handler = handlerMap.get(topicEnum);
        Serializable param = objectMapper.readValue(messageExt.getBody(), handler.getDataType());
        if (validator.getConstraintsForClass(param.getClass()).isBeanConstrained()) {
            Set<ConstraintViolation<Serializable>> noPassSet = validator.validate(param);
            if (CollectionUtils.isNotEmpty(noPassSet)) {
                ConstraintViolation<Serializable> noPass = noPassSet.iterator().next();
                log.warn("消息校验失败!reason={},msgId={},body={}",
                        (noPass.getPropertyPath() + noPass.getMessage()), messageExt.getMsgId(), body);
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        }
        return handler.process(param);
    }
}
