package com.lfx.demo.spring.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author <a href="mailto:linfx@dydf.cn">linfuxin</a>
 * @date 2020-09-01 09:59:06
 */
@ConfigurationProperties(
        prefix = "app.bean"
)
@Data
public class BeanPrintProperties {

    private boolean printBean = true;

    private boolean printBeanImpl;
}
