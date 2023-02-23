package com.lfx.demo.rocketmq.consumer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.lfx.demo.rocketmq.enums.TopicEnum;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;

import java.io.Serializable;

/**
 * @author <a href="mailto:idler41@163.con">linfuxin</a> created on 2023-02-22 11:28:44
 */
public interface NativeMessageHandler<T extends Serializable> {

    /**
     * 订阅的topic枚举
     *
     * @return 订阅的topic枚举
     */
    TopicEnum getTopic();

    /**
     * 待处理消息的数据类型
     *
     * @return 消息数据类型
     */
    TypeReference<T> getDataType();


    /**
     * 处理消息
     *
     * @param content 消息内容
     * @return ack
     */
    ConsumeConcurrentlyStatus process(T content);

}
