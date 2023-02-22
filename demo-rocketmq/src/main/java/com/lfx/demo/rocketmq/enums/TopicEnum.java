package com.lfx.demo.rocketmq.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:idler41@163.con">linfuxin</a> created on 2023-02-14 11:40:14
 */
@AllArgsConstructor
@Getter
public enum TopicEnum {
    // 消息topic
    DEMO_TOPIC("demo-topic", "tag1"),
    ;
    private final String topic;
    private final String tags;

    static final Map<String, Map<String, TopicEnum>> CACHE_MAP = new HashMap<>();
    static final Map<TopicEnum, String> DESTINATION_MAP = new HashMap<>();

    static {
        for (TopicEnum item : values()) {
            Map<String, TopicEnum> tagsMap = CACHE_MAP.computeIfAbsent(item.getTopic(), k -> new HashMap<>());
            if (tagsMap.containsKey(item.getTags())) {
                throw new RuntimeException("TopicEnum exist repeat topic and tags!");
            }
            tagsMap.put(item.getTags(), item);
            DESTINATION_MAP.put(item, item.buildDestination());
        }
    }

    public static TopicEnum getInstance(String topic, String tags) {
        Map<String, TopicEnum> tagsMap = CACHE_MAP.get(topic);
        return tagsMap == null ? null : tagsMap.get(tags);
    }

    public String getDestination() {
        return DESTINATION_MAP.get(this);
    }

    private String buildDestination() {
        return topic + ":" + tags;
    }
}
