package com.lfx.demo.rocketmq.sender;

import com.lfx.demo.rocketmq.AbstractSpringTest;
import com.lfx.demo.rocketmq.enums.TopicEnum;
import com.lfx.demo.rocketmq.message.UserMessage;
import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @author <a href="mailto:idler41@163.con">linfuxin</a> created on 2023-02-14 11:35:55
 */
public class MessageSenderTest extends AbstractSpringTest {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @BeforeClass
    public static void setup() {
        initSysProperty();
    }

    @Test
    public void simpleSendTest() throws InterruptedException {
        Long msgId = System.currentTimeMillis();
        UserMessage userMessage = new UserMessage();
        userMessage.setId((int) System.currentTimeMillis());
        Message<UserMessage> message = MessageBuilder.withPayload(userMessage).setHeader(MessageConst.PROPERTY_KEYS, msgId).build();
        rocketMQTemplate.send(TopicEnum.DEMO_TOPIC.getTopic(), message);
        Thread.sleep(10000);
    }
}
