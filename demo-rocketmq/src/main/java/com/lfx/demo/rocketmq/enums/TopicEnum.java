package com.lfx.demo.rocketmq.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author <a href="mailto:idler41@163.con">linfuxin</a> created on 2023-02-14 11:40:14
 */
@AllArgsConstructor
@Getter
public enum TopicEnum {
    // 消息topic
    DEMO_TOPIC("demo-topic"),
    ;
    private final String topic;
}
