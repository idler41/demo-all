package com.lfx.demo.rocketmq.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lfx.demo.rocketmq.constant.AppConstant;
import com.lfx.demo.rocketmq.enums.TopicEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
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

import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:idler41@163.con">linfuxin</a> created on 2023-02-14 11:44:16
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = "${rocketmq.consumer.topic:}", consumerGroup = "${rocketmq.consumer.group:}")
public class DispatchConsumer implements RocketMQListener<MessageExt>, RocketMQPushConsumerLifecycleListener,
        InitializingBean, ApplicationContextAware {

    @Value("${rocketmq.consumer.consumeThreadMax:20}")
    private Integer consumeThreadMax;

    @Value("${rocketmq.consumer.consumeThreadMin:20}")
    private Integer consumeThreadMin;

    @Autowired
    private ObjectMapper objectMapper;
    private ApplicationContext applicationContext;

    private Map<TopicEnum, MessageHandler<? super Serializable>> handlerMap;

    @Override
    public void onMessage(MessageExt messageExt) {
        // 这里改为分布式id
        MDC.put(AppConstant.TRACE_ID_KEY, String.valueOf(System.currentTimeMillis()));
        if (log.isDebugEnabled()) {
            log.info("received message,topic={},tags={},msgId={},times={}, body={}",
                    messageExt.getTopic(), messageExt.getTags(), messageExt.getMsgId(), messageExt.getReconsumeTimes(), new String(messageExt.getBody()));
        } else {
            log.info("received message,topic={},tags={},msgId={},times={}", messageExt.getTopic(), messageExt.getTags(), messageExt.getMsgId(), messageExt.getReconsumeTimes());
        }
        TopicEnum topicEnum = TopicEnum.getInstance(messageExt.getTopic(), messageExt.getTags());
        if (topicEnum == null) {
            log.warn("no message handler match!body={}", new String(messageExt.getBody()));
            return;
        }
        MessageHandler<? super Serializable> handler = handlerMap.get(topicEnum);

        Serializable content;
        try {
            content = objectMapper.readValue(messageExt.getBody(), handler.getDataType());
        } catch (IOException e) {
            // 在重试时间范围内，修复反序列化问题可防止消息丢失
            throw new RuntimeException(e);
        }
        handler.process(content);
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {
        // 参数化改造
        consumer.setConsumeThreadMax(consumeThreadMax);
        consumer.setConsumeThreadMin(consumeThreadMin);
        log.info("修改mq消费者最大线程数!");
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, MessageHandler> handlerBeanMap = applicationContext.getBeansOfType(MessageHandler.class);
        if (handlerBeanMap.isEmpty()) {
            handlerMap = Collections.emptyMap();
            log.warn("rocketmq消息处理器为空!");
            return;
        }
        handlerMap = handlerBeanMap.values().stream().collect(Collectors.toMap(MessageHandler::getTopic, v -> v));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
