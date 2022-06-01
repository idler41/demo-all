package com.lfx.demo.abstracts.saas.convert;

import com.lfx.demo.abstracts.saas.domain.SaasDomain;

/**
 * @author <a href="mailto:idler41@163.com">linfuxin</a> created by 2022-06-01 21:16:38
 */
public abstract class AbstractConvert<T extends SaasDomain, V, E> implements WebConvert<T, V>, ExcelConvert<T, E> {
}
