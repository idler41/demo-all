package com.lfx.demo.abstracts.saas.convert;

import com.lfx.demo.abstracts.domain.BaseDomain;
import com.lfx.demo.abstracts.saas.domain.SaasDomain;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import static com.lfx.demo.abstracts.constant.DomainConstant.CONVERT_CREATE_BY;
import static com.lfx.demo.abstracts.constant.DomainConstant.CONVERT_CREATE_TIME;
import static com.lfx.demo.abstracts.constant.DomainConstant.CONVERT_ID;
import static com.lfx.demo.abstracts.constant.DomainConstant.CONVERT_UPDATE_BY;
import static com.lfx.demo.abstracts.constant.DomainConstant.CONVERT_UPDATE_TIME;

/**
 * @author <a href="mailto:idler41@163.com">linfuxin</a> created by 2022-06-01 20:58:34
 */
public interface BaseDomainWebConvert<T extends BaseDomain & SaasDomain, V> extends BaseDomainConvert {

    V toVo(T t);

    @Named(value = "voToEntityIgnoreId")
    @Mapping(target = CONVERT_ID, ignore = true)
    @Mapping(target = CONVERT_CREATE_BY, expression = "java(operator())")
    @Mapping(target = CONVERT_CREATE_TIME, expression = "java(operateTime())")
    T voToEntityIgnoreId(V v);

    @Named(value = "voToEntity")
    @Mapping(target = CONVERT_UPDATE_BY, expression = "java(operator())")
    @Mapping(target = CONVERT_UPDATE_TIME, expression = "java(operateTime())")
    T voToEntity(V v);
}
