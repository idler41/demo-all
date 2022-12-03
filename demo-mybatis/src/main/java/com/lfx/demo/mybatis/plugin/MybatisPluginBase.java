package com.lfx.demo.mybatis.plugin;

import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * https://www.cnblogs.com/liu-s/p/8000293.html
 * @author <a href="mailto:idler41@163.com">linfuxin</a> created by 2022-12-02 09:00:42
 */
public abstract class MybatisPluginBase {
    // 这是指定拦截位置的注解@Signature中的args的序号
    public static final int PARAMETER_INDEX_MAPPED_STATEMENT = 0;
    public static final int PARAMETER_INDEX_PARAMETER = 1;
    public static final int PARAMETER_INDEX_ROW_BOUNDS = 2;
    public static final int PARAMETER_INDEX_RESULT_HANDLER = 3;
    public static final int PARAMETER_INDEX_CACHE_KEY = 4;
    public static final int PARAMETER_INDEX_BOUND_SQL = 5;

    /**
     * 创建参数替换的结果
     *
     * @param args 拦截方法的参数属性
     * @return 参数替换后的数据，主要是轻重的sqlMap属性
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public Map<String, Object> createSqlParameterMap(Object[] args) {

        Map<String, Object> parameterMap = new HashMap<>();
        // 当执行的mapper接口方法有参数时
        if (args[PARAMETER_INDEX_PARAMETER] != null) {
            // 如果是多个值会存储在map中
            if (args[PARAMETER_INDEX_PARAMETER] instanceof Map) {
                parameterMap = (Map) args[PARAMETER_INDEX_PARAMETER];
            } else if (BeanUtils.isSimpleValueType(args[PARAMETER_INDEX_PARAMETER].getClass())) {
                // todo 名称不对 xml获取为null
                parameterMap.putIfAbsent("id", args[PARAMETER_INDEX_PARAMETER]);
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

        additionalParam(parameterMap);
        return parameterMap;
    }

    protected abstract void additionalParam(Map<String, Object> parameterMap);
}
