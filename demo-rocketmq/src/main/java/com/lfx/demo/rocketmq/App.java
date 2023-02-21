package com.lfx.demo.rocketmq;

import com.lfx.demo.rocketmq.config.AppConfig;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author <a href="mailto:idler41@163.com">linfuxin</a> created by 2022-03-06 16:42:20
 */
public class App {

    public static void main(String[] args) {
        new SpringApplicationBuilder(AppConfig.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }
}
