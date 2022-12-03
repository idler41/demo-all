package com.lfx.demo.mapper;

import com.lfx.demo.annotation.DataScope;

import java.io.Serializable;

/**
 * @author <a href="mailto:idler41@163.com">linfuxin</a> created by 2022-12-03 13:32:33
 */
public interface BaseMapper<T> {

    T get(Serializable id);
}
