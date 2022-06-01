package com.lfx.demo.abstracts.convert;

/**
 * @author <a href="mailto:idler41@163.com">linfuxin</a> created by 2022-06-01 20:58:34
 */
public interface WebConvert<T, V> {

    V toVo(T t);

    T voToEntityIgnoreId(V v);

    T voToEntity(V v);
}
