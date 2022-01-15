package com.lfx.demo.config;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.lfx.demo.cache.FlexibleCaffeineCacheManager;
import com.lfx.demo.config.config.properties.CacheSpecMap;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * @author <a href="mailto:linfx@dydf.cn">linfuxin</a>
 * @date 2020-05-16 18:32:18
 */
@ImportAutoConfiguration({
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        TransactionAutoConfiguration.class,
        MybatisPlusAutoConfiguration.class,
})
@PropertySources({
        @PropertySource("classpath:/application.properties"),
})
@Configuration
@EnableConfigurationProperties(CacheSpecMap.class)
@EnableCaching
@MapperScan("com.lfx.demo.mapper")
@ComponentScan(value = "com.lfx.demo")
public class PersistenceConfig {

    /**
     * 本地多配置缓存
     * @param cacheSpecMap ...
     * @return ...
     */
    @Bean
    public CacheManager cacheManager(CacheSpecMap cacheSpecMap) {
        return new FlexibleCaffeineCacheManager(cacheSpecMap.getSpecs());
    }

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
