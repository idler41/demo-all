package com.lfx.demo.mq.config;

import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.bean.OrderProducerBean;
import com.aliyun.openservices.ons.api.bean.ProducerBean;
import com.aliyun.openservices.ons.api.bean.TransactionProducerBean;
import com.lfx.demo.mq.producer.OrderProducerBeanHelper;
import com.lfx.demo.mq.producer.ProducerBeanHelper;
import com.lfx.demo.mq.producer.TxProducerBeanHelper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.lang.reflect.Field;
import java.util.Properties;

/**
 * @author <a href="mailto:linfx@dydf.cn">linfuxin</a>
 * @date 2020-09-07 15:29:37
 */
@Configuration
public class ONSAutoConfig implements EnvironmentAware {

    private Environment env;

    @Override
    public void setEnvironment(Environment environment) {
        this.env = environment;
    }

    @ConditionalOnProperty(prefix = "mq.ons.producer", name = "NAMESRV_ADDR")
    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public ProducerBean producerBean() {
        Properties props = resolveMQProperties("mq.ons.producer");
        ProducerBean instance = new ProducerBean();
        instance.setProperties(props);
        return instance;
    }

    @Bean
    @ConditionalOnBean(ProducerBean.class)
    public ProducerBeanHelper producerBeanHelper(ProducerBean producerBean) {
        return new ProducerBeanHelper(producerBean);
    }

    @ConditionalOnProperty(prefix = "mq.ons.orderProducer", name = "NAMESRV_ADDR")
    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public OrderProducerBean orderProducerBean() {
        Properties props = resolveMQProperties("mq.ons.orderProducer");
        OrderProducerBean instance = new OrderProducerBean();
        instance.setProperties(props);
        return instance;
    }

    @Bean
    @ConditionalOnBean(OrderProducerBean.class)
    public OrderProducerBeanHelper orderProducerBeanHelper(OrderProducerBean orderProducerBean) {
        return new OrderProducerBeanHelper(orderProducerBean);
    }

    @ConditionalOnProperty(prefix = "mq.ons.transactionProducer", name = "NAMESRV_ADDR")
    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public TransactionProducerBean transactionProducerBean() {
        Properties props = resolveMQProperties("mq.ons.orderProducer");
        TransactionProducerBean instance = new TransactionProducerBean();
        instance.setProperties(props);
        return instance;
    }

    @Bean
    @ConditionalOnBean(TransactionProducerBean.class)
    public TxProducerBeanHelper txProducerBeanHelper(TransactionProducerBean txProducerBean) {
        return new TxProducerBeanHelper(txProducerBean);
    }

    private Properties resolveMQProperties(String prefix) {
        Properties properties = new Properties();
        Field[] fields = PropertyKeyConst.class.getDeclaredFields();
        try {
            for (Field field : fields) {
                String key = field.get(null).toString();
                Object val = env.getProperty(prefix + "." + key);
                //noinspection ConstantConditions
                if (val != null) {
                    properties.put(key, val);
                }
            }
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("解析mq配置信息时出现异常!");
        }
        return properties;
    }
}
