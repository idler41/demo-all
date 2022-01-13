package com.lfx.demo.config.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

/**
 * Created on 2022-01-13 14:53:03
 *
 * @author linfuxin
 */
@Slf4j
@Component
public class DefaultMessageHandler implements MessageHandler {

    @Autowired
    private MqttSender mqttSender;

    @ServiceActivator(inputChannel = "inputChannel")
    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        log.info("receive {}", message);
        mqttSender.sendToMqtt("test-123", "123");
    }
}
