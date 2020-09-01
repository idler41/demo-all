package com.lfx.demo.spring.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="mailto:linfx@dydf.cn">linfuxin</a>
 * @date 2020-09-01 10:52:14
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(BeanPrintProperties.class)
public class BeanPrintAutoConfig {
}
