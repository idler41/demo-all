package com.lfx.demo.config.config.properties;

import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;


/**
 * Created on 2022-01-13 10:58:05
 *
 * @author linfuxin
 */
@Data
@ConfigurationProperties(prefix = "spring.mqtt")
public class MqttProperties {

    private Connection connection;
    private Producer producer;
    private Consumer consumer;

    @Data
    public static final class Connection {
        private List<String> url;
        private String username;
        private String password;
        private Integer timeout;
        private Integer keepAliveInterval;
        private Integer completionTimeout;
        private boolean cleanSession = true;
        private boolean autoReconnect = true;
        private Integer maxReconnectDelay = 128000;
        private Integer executorServiceTimeout = 1;
    }

    @Data
    public static final class Producer {
        private String clientId;
        private boolean async = true;
        private boolean asyncEvent = true;
    }

    @Data
    public static final class Consumer {
        private String clientId;
        private boolean manualAck;
        private Integer threadCorePoolSize;
        private Integer threadMaxPoolSize;
        private Integer threadWorkQueueSize;
        private Integer threadKeepAliveTime;
        private List<SubTopic> topic;
    }

    @Data
    public static final class SubTopic {
        private String name;
        private Integer qos;
    }
}
