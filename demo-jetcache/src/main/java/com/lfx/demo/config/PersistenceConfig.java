package com.lfx.demo.config;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import com.alicp.jetcache.anno.support.SpringConfigProvider;
import com.alicp.jetcache.autoconfigure.JetCacheAutoConfiguration;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.lfx.demo.jetcache.JacksonKeyConvertor;
import com.lfx.demo.jetcache.JacksonValueDecoder;
import com.lfx.demo.jetcache.JacksonValueEncoder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import java.util.function.Function;

/**
 * @author <a href="mailto:linfx@dydf.cn">linfuxin</a>
 * @date 2020-05-16 18:32:18
 */
@ImportAutoConfiguration({
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        TransactionAutoConfiguration.class,
        MybatisPlusAutoConfiguration.class,
        JetCacheAutoConfiguration.class,
})
@PropertySources({
        @PropertySource("classpath:/application.properties"),
})
@Configuration
@MapperScan("com.lfx.demo.mapper")
@ComponentScan(value = "com.lfx.demo")
@EnableMethodCache(basePackages = "com.lfx.demo.manager")
@EnableCreateCacheAnnotation
public class PersistenceConfig {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    @Bean
    public JacksonKeyConvertor jacksonRedisKeyConvertor() {
        return new JacksonKeyConvertor();
    }

    @Bean
    public JacksonValueDecoder jacksonValueDecoder() {
        return new JacksonValueDecoder();
    }

    @Bean
    public JacksonValueEncoder jacksonValueEncoder() {
        return new JacksonValueEncoder();
    }

    @Bean
    public SpringConfigProvider springConfigProvider(
            JacksonKeyConvertor jacksonRedisKeyConvertor,
            JacksonValueDecoder jacksonValueDecoder,
            JacksonValueEncoder jacksonValueEncoder
    ) {
        return new SpringConfigProvider() {

            @Override
            public Function<Object, Object> parseKeyConvertor(String convertor) {
                if ("jackson".equalsIgnoreCase(convertor)) {
                    return jacksonRedisKeyConvertor;
                } else {
                    return super.parseKeyConvertor(convertor);
                }
            }

            @Override
            public Function<Object, byte[]> parseValueEncoder(String valueEncoder) {
                if ("jackson".equalsIgnoreCase(valueEncoder)) {
                    return jacksonValueEncoder;
                } else {
                    return super.parseValueEncoder(valueEncoder);
                }
            }

            @Override
            public Function<byte[], Object> parseValueDecoder(String valueDecoder) {
                if ("jackson".equalsIgnoreCase(valueDecoder)) {
                    return jacksonValueDecoder;
                } else {
                    return super.parseValueDecoder(valueDecoder);
                }
            }
        };
    }
}
