package com.lfx.demo.mq.producer;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.SendResult;
import com.aliyun.openservices.ons.api.bean.OrderProducerBean;
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
public class OrderProducerBeanHelper implements InitializingBean {

    private final OrderProducerBean orderProducerBean;

    public OrderProducerBeanHelper(OrderProducerBean orderProducerBean) {
        this.orderProducerBean = orderProducerBean;
    }

    @Override
    public void afterPropertiesSet() {
        if (orderProducerBean == null) {
            throw new IllegalStateException(OrderProducerBean.class.getCanonicalName() + " is null!");
        }
    }

    public <T> void send(MQTopic mqTopic, T data, String shardingKey, MessageSerializer serializer) {
        send(mqTopic, null, serializer.serialize(data), shardingKey);
    }

    public <T> void send(MQTopic mqTopic, String msgKey, T data, String shardingKey, MessageSerializer serializer) {
        send(mqTopic, msgKey, serializer.serialize(data), shardingKey);
    }

    public <T> void send(MQTopic mqTopic, byte[] bytes, String shardingKey) {
        send(mqTopic, null, bytes, shardingKey);
    }

    public <T> void send(MQTopic mqTopic, String msgKey, byte[] bytes, String shardingKey) {
        SendResult sendResult = orderProducerBean.send(new Message(mqTopic.getTopic(), mqTopic.getTag(), msgKey, bytes), shardingKey);
        if (sendResult != null) {
            log.info("topic={},tag={},key={},msgId={} send message success!", mqTopic.getTopic(), mqTopic.getTag(), msgKey, sendResult.getMessageId());
        }
    }
}
