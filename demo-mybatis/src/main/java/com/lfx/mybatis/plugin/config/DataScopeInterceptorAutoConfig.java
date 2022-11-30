package com.lfx.mybatis.plugin.config;

import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
import com.lfx.demo.mybatis.plugin.DataScopeInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 使用@AutoConfigureBefore调整配置顺序竟没生效？
 * 1. 不能被springboot扫描到，否则优先级更高会被优先加载
 * 2. 在spring.factories中配置，然后@AutoConfigureAfter才会生效
 * @author <a href="mailto:idler41@163.com">linfuxin</a> created by 2022-11-30 16:03:32
 */
@Configuration
@AutoConfigureAfter(PageHelperAutoConfiguration.class)
public class DataScopeInterceptorAutoConfig implements InitializingBean {

    @Autowired
    private List<SqlSessionFactory> sqlSessionFactoryList;

    @Override
    public void afterPropertiesSet() throws Exception {
        for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
            org.apache.ibatis.session.Configuration configuration = sqlSessionFactory.getConfiguration();
            //自己添加
            configuration.addInterceptor(new DataScopeInterceptor());
        }
    }
}
