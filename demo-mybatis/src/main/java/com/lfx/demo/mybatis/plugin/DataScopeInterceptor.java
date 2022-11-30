package com.lfx.demo.mybatis.plugin;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Properties;

/**
 * https://mybatis.org/mybatis-3/zh/configuration.html#plugins
 *
 * MyBatis 允许你在映射语句执行过程中的某一点进行拦截调用。默认情况下，MyBatis 允许使用插件来拦截的方法调用包括：
 * <p>
 * Executor (update, query, flushStatements, commit, rollback, getTransaction, close, isClosed)
 * ParameterHandler (getParameterObject, setParameters)
 * ResultSetHandler (handleResultSets, handleOutputParameters)
 * StatementHandler (prepare, parameterize, batch, update, query)
 *
 * @author <a href="mailto:idler41@163.com">linfuxin</a> created by 2022-11-05 13:51:00
 */
//@Component
@Intercepts({
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class,Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
})
public class DataScopeInterceptor implements Interceptor {

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override

    public void setProperties(Properties properties) {
        Interceptor.super.setProperties(properties);
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 1. 只拦截有DataScope的方法 && 配置了动态标签的sql

        // 2. 获取所有组织

        // 3. 设置额外参数
        // 参数代理
        if (invocation.getTarget() instanceof ParameterHandler) {
            System.out.println("ParameterHandler");
            // 自动添加操作员信息
            autoAddOperatorInfo(invocation);
        }
//        List<ParameterMapping> parameterMappingList = ((MappedStatement) invocation.getArgs()[0]).getParameterMap().getParameterMappings();
//        parameterMappingList.add()
        return invocation.proceed();
    }

    private void autoAddOperatorInfo(Invocation invocation) {
        // 获取代理的参数对象ParameterHandler
        ParameterHandler ph = (ParameterHandler) invocation.getTarget();

    }

}
