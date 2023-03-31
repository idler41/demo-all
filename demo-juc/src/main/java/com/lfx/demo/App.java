package com.lfx.demo;

import com.lfx.demo.config.AppConfig;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author <a href="mailto:idler41@163.con">linfuxin</a> created on 2023-03-31 08:54:36
 */
public class App {

    public static void main(String[] args) {
        new SpringApplicationBuilder(AppConfig.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }
}
