package com.lfx.demo.weblog.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lfx.demo.weblog.filter.TokenFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 * @author <a href="mailto:linfx@dydf.cn">linfuxin</a>
 * @date 2020-08-28 11:27:00
 */
@Configuration
@ConditionalOnClass(TokenFilter.class)
public class TokenFilterAutoConfig {

    @ConditionalOnMissingBean(ObjectMapper.class)
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @ConditionalOnMissingBean(TokenFilter.class)
    @Bean
    public FilterRegistrationBean<TokenFilter> requestUrlFilter(ObjectMapper objectMapper) {

        FilterRegistrationBean<TokenFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new TokenFilter(objectMapper));
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("requestLogFilter");
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registrationBean;
    }

}
