package com.lfx.demo.rocketmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Component;

/**
 * 配置文件配置线程池?
 *
 * @author <a href="mailto:idler41@163.con">linfuxin</a> created on 2023-02-14 11:44:16
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = "demo-topic", consumerGroup = "demo-consumer-group")
public class SimpleConsumer implements RocketMQListener<MessageExt>, RocketMQPushConsumerLifecycleListener {
    @Override
    public void onMessage(MessageExt messageExt) {
        log.info("received messageExt: {}", messageExt);
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {
        // 参数化改造
        consumer.setConsumeThreadMax(21);
        log.info("修改mq消费者最大线程数!");
    }
}
