package com.lfx.demo.rocketmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 注意 @RocketMQMessageListener的topic与consumerGroup不要使用rocketmq.consumer.topic等配置。 DefaultLitePullConsumer也会使用该配置，启动后2个consumer，
 * 但是最终只有一个Client(DefaultMQPushConsumer)与Broker通信。造成的结果就是1个clientId，2个cid, 分配queue时，只分配一半的queue，导致部分消息永远消费不了
 *
 * @see org.apache.rocketmq.client.consumer.rebalance.AllocateMessageQueueAveragely
 *
 * @author <a href="mailto:idler41@163.con">linfuxin</a> created on 2023-02-14 11:44:16
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = "${rocketmq.container.consumer.topic:}", consumerGroup = "${rocketmq.container.consumer.group:}", selectorExpression = "${rocketmq.container.consumer.tags:*}")
public class NativeMessageListener implements RocketMQListener<MessageExt>, RocketMQPushConsumerLifecycleListener {

    @Value("${rocketmq.container.consumer.consumeThreadMax:1}")
    private Integer consumeThreadMax;

    @Value("${rocketmq.container.consumer.consumeThreadMin:20}")
    private Integer consumeThreadMin;

    @Autowired
    private NativeDispatchConsumer nativeDispatchConsumer;

    @Override
    public void onMessage(MessageExt messageExt) {
        throw new UnsupportedOperationException();
    }


    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {
        // 参数化改造
        consumer.setConsumeThreadMax(consumeThreadMax);
        consumer.setConsumeThreadMin(consumeThreadMin);
        // 为了手动发送ack，这里改为rocketmq原生监听器
        consumer.setMessageListener(nativeDispatchConsumer);
        if (log.isDebugEnabled()) {
            log.debug("参数化改造mq消费者线程数!instanceName={}", consumer.getInstanceName());
        }
    }
}
