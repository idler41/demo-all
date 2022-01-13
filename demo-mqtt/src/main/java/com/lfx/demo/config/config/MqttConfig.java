package com.lfx.demo.config.config;

import com.lfx.demo.config.config.properties.MqttProperties;
import com.lfx.demo.config.handler.DefaultMessageHandler;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.ExecutorChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created on 2021-12-22 17:17:45
 *
 * @author linfuxin
 */
@Configuration
@EnableConfigurationProperties(MqttProperties.class)
@EnableIntegration
@IntegrationComponentScan(basePackages = "com.lfx.demo.config.handler")
public class MqttConfig {

    @Autowired
    private MqttProperties properties;

    @Bean
    public MqttPahoClientFactory clientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(buildConnectOptions(properties));
        return factory;
    }

    @Bean(name = "inputChannel")
    public MessageChannel inputChannel() {
        ThreadFactory threadFactory = new ThreadFactory() {
            final AtomicInteger tag = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("mqttConsumerThread-" + tag.incrementAndGet());
                return thread;
            }
        };

        ThreadPoolExecutor executorService = new ThreadPoolExecutor(
                properties.getConsumer().getThreadCorePoolSize(),
                properties.getConsumer().getThreadMaxPoolSize(),
                properties.getConsumer().getThreadKeepAliveTime(),
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(properties.getConsumer().getThreadWorkQueueSize()),
                threadFactory);
        return new ExecutorChannel(executorService);
    }

    @Bean
    public MqttPahoMessageDrivenChannelAdapter inbound() {
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(properties.getConsumer().getClientId(), clientFactory());
        adapter.setOutputChannel(inputChannel());
        properties.getConsumer().getTopic().forEach(item -> adapter.addTopic(item.getName(), item.getQos()));
        adapter.setManualAcks(properties.getConsumer().isManualAck());
        return adapter;
    }

    @Bean(name = "outputChannel")
    public MessageChannel mqttOutputChannel() {
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "outputChannel")
    public MessageHandler outbound() {
        MqttPahoMessageHandler messageHandler =
                new MqttPahoMessageHandler(properties.getProducer().getClientId(), clientFactory());
        messageHandler.setAsync(properties.getProducer().isAsync());
        messageHandler.setAsyncEvents(properties.getProducer().isAsyncEvent());
        return messageHandler;
    }

    private MqttConnectOptions buildConnectOptions(MqttProperties properties) {
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setServerURIs(properties.getConnection().getUrl().toArray(new String[]{}));
        mqttConnectOptions.setUserName(properties.getConnection().getUsername());
        mqttConnectOptions.setPassword(properties.getConnection().getPassword().toCharArray());
        mqttConnectOptions.setConnectionTimeout(properties.getConnection().getTimeout());
        mqttConnectOptions.setKeepAliveInterval(properties.getConnection().getKeepAliveInterval());
        mqttConnectOptions.setCleanSession(properties.getConnection().isCleanSession());
        mqttConnectOptions.setAutomaticReconnect(properties.getConnection().isAutoReconnect());
        mqttConnectOptions.setMaxReconnectDelay(properties.getConnection().getMaxReconnectDelay());
        mqttConnectOptions.setExecutorServiceTimeout(properties.getConnection().getExecutorServiceTimeout());
        return mqttConnectOptions;
    }

}
