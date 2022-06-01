package com.lfx.demo.abstracts.saas.convert;

import com.lfx.demo.abstracts.saas.domain.SaasDomain;

/**
 * @author <a href="mailto:idler41@163.com">linfuxin</a> created by 2022-06-01 20:58:34
 */
public interface ExcelConvert<T extends SaasDomain, E> {

    E toExcel(T t);

    T excelToEntityIgnoreId(E e);

    T excelToEntity(E e);
}
