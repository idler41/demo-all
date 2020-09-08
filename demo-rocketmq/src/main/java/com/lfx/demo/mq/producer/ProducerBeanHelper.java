package com.lfx.demo.mq.producer;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.SendResult;
import com.aliyun.openservices.ons.api.bean.ProducerBean;
import com.lfx.demo.mq.enums.MQTopic;
import com.lfx.demo.mq.serializer.MessageSerializer;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author <a href="mailto:linfx@dydf.cn">linfuxin</a>
 * @date 2020-09-08 09:52:03
 */
@Slf4j
@Getter
public class ProducerBeanHelper implements InitializingBean {

    private final ProducerBean producerBean;

    public ProducerBeanHelper(ProducerBean producerBean) {
        this.producerBean = producerBean;
    }

    @Override
    public void afterPropertiesSet() {
        if (producerBean == null) {
            throw new IllegalStateException(ProducerBean.class.getCanonicalName() + " is null!");
        }
    }

    public <T> void send(MQTopic mqTopic, T data, MessageSerializer serializer) {
        send(mqTopic, null, serializer.serialize(data));
    }

    public <T> void send(MQTopic mqTopic, String key, T data, MessageSerializer serializer) {
        send(mqTopic, key, serializer.serialize(data));
    }

    public <T> void send(MQTopic mqTopic, byte[] bytes) {
        send(mqTopic, null, bytes);
    }

    public <T> void send(MQTopic mqTopic, String key, byte[] bytes) {
        SendResult sendResult = producerBean.send(new Message(mqTopic.getTopic(), mqTopic.getTag(), key, bytes));
        if (sendResult != null) {
            log.info("topic={},tag={},key={},msgId={} send message success!", mqTopic.getTopic(), mqTopic.getTag(), key, sendResult.getMessageId());
        }
    }
}
