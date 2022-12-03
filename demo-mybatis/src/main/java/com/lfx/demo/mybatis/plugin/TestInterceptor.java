
package com.lfx.demo.mybatis.plugin;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

/**
 * https://mybatis.org/mybatis-3/zh/configuration.html#plugins
 * <p>
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
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
})
public class TestInterceptor extends MybatisPluginBase implements Interceptor {

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
        // 获取到拦截方法的参数
        Object[] args = invocation.getArgs();
        // 创建sql参数的map
        args[PARAMETER_INDEX_PARAMETER] = createSqlParameterMap(args);
        // 创建新的调用对象（参数被覆盖）
        Invocation overrideInvocation = new Invocation(invocation.getTarget(), invocation.getMethod(), args);
        // 运行新的调用对象
        return overrideInvocation.proceed();
    }

    @Override
    protected void additionalParam(Map<String, Object> parameterMap) {
        parameterMap.put("params.orgIds", Arrays.asList("org1", "org2"));

    }
}
