package com.lfx.demo.rocketmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:idler41@163.con">linfuxin</a> created on 2023-02-14 11:44:16
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = "demo-topic",consumerGroup = "demo-consumer-group")
public class SimpleConsumer implements RocketMQListener<MessageExt> {
    @Override
    public void onMessage(MessageExt messageExt) {
        log.info("received messageExt: {}", messageExt);
    }
}
