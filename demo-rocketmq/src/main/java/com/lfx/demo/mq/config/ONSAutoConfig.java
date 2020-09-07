package com.lfx.demo.mq.config;

import com.aliyun.openservices.ons.api.bean.OrderProducerBean;
import com.aliyun.openservices.ons.api.bean.ProducerBean;
import com.aliyun.openservices.ons.api.bean.TransactionProducerBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="mailto:linfx@dydf.cn">linfuxin</a>
 * @date 2020-09-07 15:29:37
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(MQProperties.class)
public class ONSAutoConfig {


    @ConditionalOnProperty(name = "mq.ons.producer.enable", havingValue = "true")
    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public ProducerBean producerBean(MQProperties mqProps) {
        ProducerBean instance = new ProducerBean();
        instance.setProperties(mqProps.getProperties());
        return instance;
    }

    @ConditionalOnProperty(name = "mq.ons.orderProducer.enable", havingValue = "true")
    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public OrderProducerBean orderProducerBean(MQProperties mqProps) {
        OrderProducerBean instance = new OrderProducerBean();
        instance.setProperties(mqProps.getProperties());
        return instance;
    }

    @ConditionalOnProperty(name = "mq.ons.producer.enable", havingValue = "true")
    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public TransactionProducerBean transactionProducerBean(MQProperties mqProps) {
        TransactionProducerBean instance = new TransactionProducerBean();
        instance.setProperties(mqProps.getProperties());
        return instance;
    }
}
