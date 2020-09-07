package com.lfx.demo.mq.config;

import com.aliyun.openservices.ons.api.PropertyKeyConst;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import java.lang.reflect.Field;
import java.util.Properties;

/**
 * @author <a href="mailto:linfx@dydf.cn">linfuxin</a>
 * @date 2020-09-07 17:41:25
 */
@ConfigurationProperties(prefix = "mq.ons")
@Getter
public class MQProperties implements EnvironmentAware {
    private Properties properties;

    @Override
    public void setEnvironment(Environment env) {
        Properties props = new Properties();
        Field[] fields = PropertyKeyConst.class.getDeclaredFields();
        String name = "mq.ons.producer.";
        try {
            for (Field field : fields) {
                String key = field.get(null).toString();
                Object val = env.getProperty(name + key);
                if (val != null) {
                    props.put(key, val);
                }
            }
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("解析mq配置信息时出现异常!");
        }
        this.properties = props;
    }
}
