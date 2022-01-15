package com.lfx.demo.config;

import com.lfx.demo.config.config.AppConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

/**
 * Created on 2022-01-13 11:13:38
 *
 * @author linfuxin
 */
public class App implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(AppConfig.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Thread.currentThread().join();
    }
}
