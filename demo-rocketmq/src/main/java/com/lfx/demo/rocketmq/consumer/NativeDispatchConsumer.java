package com.lfx.demo.rocketmq.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lfx.demo.rocketmq.constant.AppConstant;
import com.lfx.demo.rocketmq.enums.TopicEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.MDC;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
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
public class NativeDispatchConsumer implements MessageListenerConcurrently, InitializingBean, ApplicationContextAware {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Validator validator;

    private ApplicationContext applicationContext;

    private Map<TopicEnum, NativeMessageHandler<? super Serializable>> handlerMap;

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
        // TODO 这里先用时间戳，后期改为分布式id
        MDC.put(AppConstant.TRACE_ID_KEY, String.valueOf(System.nanoTime()));
        String body = new String(messageExt.getBody());
        if (log.isDebugEnabled()) {
            log.debug("received message,msgId={},msgKey={},broker={},queueId={},queueOffset={},times={}",
                    messageExt.getMsgId(), messageExt.getKeys(), messageExt.getBrokerName(),
                    messageExt.getQueueId(), messageExt.getQueueOffset(), messageExt.getReconsumeTimes());
        } else {
            log.info("received message,msgId={},msgKey={},times={}", messageExt.getMsgId(), messageExt.getKeys(), messageExt.getReconsumeTimes());
        }
        TopicEnum topicEnum = TopicEnum.getInstance(messageExt.getTopic(), messageExt.getTags());
        if (topicEnum == null) {
            // 同一消费组发生订阅关系变化出现不一致时，可能接收到未订阅的消息
            log.warn("no message handler match!topic={},tags={}", messageExt.getTopic(), messageExt.getTags());
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
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        }
        return handler.process(param);
    }
}
