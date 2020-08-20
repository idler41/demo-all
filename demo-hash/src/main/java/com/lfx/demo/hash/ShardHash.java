package com.lfx.demo.hash;

/**
 * @author <a href="mailto:linfx@dydf.cn">linfuxin</a>
 * @date 2020-08-20 15:27:38
 */
public interface ShardHash<T> {

    int hash(T t);
}
