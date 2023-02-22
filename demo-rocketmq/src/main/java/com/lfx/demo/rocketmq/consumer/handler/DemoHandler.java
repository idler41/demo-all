package com.lfx.demo.rocketmq.consumer.handler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.lfx.demo.rocketmq.consumer.MessageHandler;
import com.lfx.demo.rocketmq.enums.TopicEnum;
import com.lfx.demo.rocketmq.message.UserMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:idler41@163.con">linfuxin</a> created on 2023-02-22 15:20:52
 */
@Component
@Slf4j
public class DemoHandler implements MessageHandler<UserMessage> {
    private static final TypeReference<UserMessage> DATA_TYPE = new TypeReference<UserMessage>() {
    };

    @Override
    public TopicEnum getTopic() {
        return TopicEnum.DEMO_TOPIC;
    }

    @Override
    public TypeReference<UserMessage> getDataType() {
        return DATA_TYPE;
    }

    @Override
    public void process(UserMessage content) {
        log.info("handler receive message! content={}", content);
    }
}
