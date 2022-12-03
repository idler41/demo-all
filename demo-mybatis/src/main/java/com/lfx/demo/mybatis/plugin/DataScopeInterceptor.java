
package com.lfx.demo.mybatis.plugin;

import com.lfx.demo.annotation.DataScope;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
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
public class DataScopeInterceptor implements Interceptor {

    private final Map<String, DataScope> dataScopeCache;

    public DataScopeInterceptor(Configuration configuration) {
        dataScopeCache = new HashMap<>();
        for (MappedStatement mappedStatement : configuration.getMappedStatements()) {
            DataScope dataScope = resolveDataScopeIfPossible(mappedStatement);
            dataScopeCache.put(mappedStatement.getId(), dataScope);
        }
    }

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

        MappedStatement mappedStatement = (MappedStatement) args[PARAMETER_INDEX_MAPPED_STATEMENT];
        DataScope dataScope = dataScopeCache.get(mappedStatement.getId());
        // 只拦截select语句
        if (SqlCommandType.SELECT == mappedStatement.getSqlCommandType() && dataScope != null) {
            // 创建sql参数的map
            args[PARAMETER_INDEX_PARAMETER] = createSqlParameterMap(args, dataScope);
            // 运行新的调用对象（参数被覆盖）
            return new Invocation(invocation.getTarget(), invocation.getMethod(), args).proceed();
        }
        return invocation.proceed();
    }

    protected void additionalParam(Map<String, Object> parameterMap, DataScope dataScope) {
        parameterMap.put(dataScope.scopeParam(), Arrays.asList("org1", "org2"));
    }

    // 这是指定拦截位置的注解@Signature中的args的序号
    public static final int PARAMETER_INDEX_MAPPED_STATEMENT = 0;
    public static final int PARAMETER_INDEX_PARAMETER = 1;
    public static final int PARAMETER_INDEX_ROW_BOUNDS = 2;
    public static final int PARAMETER_INDEX_RESULT_HANDLER = 3;
    public static final int PARAMETER_INDEX_CACHE_KEY = 4;
    public static final int PARAMETER_INDEX_BOUND_SQL = 5;

    private Map<String, Object> createSqlParameterMap(Object[] args, DataScope dataScope) {

        Map<String, Object> parameterMap = new HashMap<>();
        // 当执行的mapper接口方法有参数时
        if (args[PARAMETER_INDEX_PARAMETER] != null) {
            // 如果是多个值会存储在map中
            if (args[PARAMETER_INDEX_PARAMETER] instanceof Map) {
                parameterMap = (Map) args[PARAMETER_INDEX_PARAMETER];
                if (!dataScope.requestParam().equals("-undefined-")) {
                    // 单个数组集合作为参数，则需要添加命名参数
                    parameterMap.put(dataScope.requestParam(), parameterMap.get(dataScope.argIndex()));
                }
            } else if (BeanUtils.isSimpleValueType(args[PARAMETER_INDEX_PARAMETER].getClass())) {
                parameterMap.putIfAbsent(dataScope.requestParam(), args[PARAMETER_INDEX_PARAMETER]);
            } else {
                // 如果是DTO类型，反射获取字段和值的map
                Object dto = args[PARAMETER_INDEX_PARAMETER];
                // 获取属性
                PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(dto.getClass());
                for (PropertyDescriptor p : propertyDescriptors) {
                    // spring的BeanUtils.getPropertyDescriptors会把class也获取到
                    if (p.getPropertyType().equals(Class.class)) continue;

                    Object value = null;
                    try {
                        // 读取实际值
                        value = p.getReadMethod().invoke(dto);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    parameterMap.putIfAbsent(p.getName(), value);
                }
            }
        }

        additionalParam(parameterMap, dataScope);
        return parameterMap;
    }

    private DataScope resolveDataScopeIfPossible(MappedStatement mappedStatement) {
        int methodStartIndex = mappedStatement.getId().lastIndexOf(".");
        Class<?> mapper = null;
        try {
            mapper = Class.forName(mappedStatement.getId().substring(0, methodStartIndex));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String methodName = mappedStatement.getId().substring(methodStartIndex + 1);
        for (Method method : mapper.getDeclaredMethods()) {
            if (method.isAnnotationPresent(DataScope.class) && (methodName.equals(method.getName()))) {
                return method.getAnnotation(DataScope.class);
            }
        }
        return null;
    }
}
