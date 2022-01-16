package com.lfx.demo.config.config.properties;

import lombok.Data;
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
    private Publisher publisher;
    private Subscriber subscriber;

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
    public static final class Publisher {
        private String clientId;
        private boolean async = true;
        private boolean asyncEvent = true;
//        private List<Topic> topics;
    }

    @Data
    public static final class Subscriber {
        private String clientId;
        private boolean manualAck;
        private Integer threadCorePoolSize;
        private Integer threadMaxPoolSize;
        private Integer threadWorkQueueSize;
        private Integer threadKeepAliveTime;
        private List<Topic> topics;
    }

    @Data
    public static final class Topic {
        private String name;
        private Integer qos;
    }
}
