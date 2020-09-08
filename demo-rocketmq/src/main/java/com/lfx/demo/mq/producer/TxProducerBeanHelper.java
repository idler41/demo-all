package com.lfx.demo.mq.producer;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.SendResult;
import com.aliyun.openservices.ons.api.bean.TransactionProducerBean;
import com.aliyun.openservices.ons.api.transaction.LocalTransactionExecuter;
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
public class TxProducerBeanHelper implements InitializingBean {

    private final TransactionProducerBean transactionProducerBean;

    public TxProducerBeanHelper(TransactionProducerBean transactionProducerBean) {
        this.transactionProducerBean = transactionProducerBean;
    }

    @Override
    public void afterPropertiesSet() {
        if (transactionProducerBean == null) {
            throw new IllegalStateException(TransactionProducerBean.class.getCanonicalName() + " is null!");
        }
    }

    public <T> void send(MQTopic mqTopic, T data, String shardingKey, MessageSerializer serializer, LocalTransactionExecuter executor, Object arg) {
        send(mqTopic, null, serializer.serialize(data), executor, arg);
    }

    public <T> void send(MQTopic mqTopic, String msgKey, T data, String shardingKey, MessageSerializer serializer, LocalTransactionExecuter executor, Object arg) {
        send(mqTopic, msgKey, serializer.serialize(data), executor, arg);
    }

    public <T> void send(MQTopic mqTopic, byte[] bytes, LocalTransactionExecuter executor, Object arg) {
        send(mqTopic, null, bytes, executor, arg);
    }

    public <T> void send(MQTopic mqTopic, String msgKey, byte[] bytes, LocalTransactionExecuter executor, Object arg) {
        SendResult sendResult = transactionProducerBean.send(new Message(mqTopic.getTopic(), mqTopic.getTag(), msgKey, bytes), executor, arg);
        if (sendResult != null) {
            log.info("topic={},tag={},key={},msgId={} send message success!", mqTopic.getTopic(), mqTopic.getTag(), msgKey, sendResult.getMessageId());
        }
    }
}
