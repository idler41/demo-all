package com.lfx.demo.abstracts.convert;

import com.lfx.demo.abstracts.domain.BaseDomain;

/**
 * @author <a href="mailto:idler41@163.com">linfuxin</a> created by 2022-06-01 21:16:38
 */
public abstract class AbstractBaseDomainConvert<T extends BaseDomain, V, E>
        implements BaseDomainWebConvert<T, V>, BaseDomainExcelConvert<T, E> {
}
