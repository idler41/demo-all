package com.lfx.demo.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author <a href="mailto:idler41@163.con">linfuxin</a> created on 2023-03-31 10:43:41
 */
@SpringBootApplication(scanBasePackages = "com.lfx.demo")
@EnableScheduling
public class AppConfig {
}
