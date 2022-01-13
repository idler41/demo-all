package com.lfx.demo.config.handler;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;

/**
 * Created on 2022-01-13 16:13:41
 *
 * @author linfuxin
 */
@MessagingGateway(defaultRequestChannel = "outputChannel")
public interface MqttSender {

    /**
     * 向默认的 topic 发送消息
     *
     * @param payload 消息内容
     */
    void sendToMqtt(String payload);

    /**
     * 向指定的 topic 发送消息
     *
     * @param topic   topic
     * @param payload 消息内容
     */
    void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic, String payload);

    /**
     * @param topic   topic
     * @param qos     服务质量参数
     * @param payload 消息内容
     */
    void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic, @Header(MqttHeaders.QOS) int qos, String payload);

    /**
     * topic
     *
     * @param topic    topic
     * @param qos      服务质量参数
     * @param retained retained
     * @param payload  消息内容
     */
    void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic, @Header(MqttHeaders.QOS) int qos, @Header(MqttHeaders.RETAINED) boolean retained, String payload);
}
