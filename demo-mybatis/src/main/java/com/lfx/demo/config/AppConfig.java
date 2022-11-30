package com.lfx.demo.config;

import com.lfx.demo.mybatis.plugin.DataScopeInterceptor;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author <a href="mailto:idler41@163.com">linfuxin</a> created by 2022-01-31 10:35:24
 */
@SpringBootApplication(scanBasePackages = {"com.lfx.demo"})
@MapperScan("com.lfx.demo.mapper")
@EnableTransactionManagement
public class AppConfig {
}
