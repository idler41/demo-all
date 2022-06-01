package com.lfx.demo.abstracts.convert;

import com.lfx.demo.abstracts.domain.BaseDomain;
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
public interface BaseDomainExcelConvert<T extends BaseDomain, E> extends BaseDomainConvert {

    E toExcel(T t);

    @Named(value = "excelToEntityIgnoreId")
    @Mapping(target = CONVERT_ID, ignore = true)
    @Mapping(target = CONVERT_CREATE_BY, expression = "java(operator())")
    @Mapping(target = CONVERT_CREATE_TIME, expression = "java(operateTime())")
    T excelToEntityIgnoreId(E e);

    @Named(value = "excelToEntity")
    @Mapping(target = CONVERT_UPDATE_BY, expression = "java(operator())")
    @Mapping(target = CONVERT_UPDATE_TIME, expression = "java(operateTime())")
    T excelToEntity(E e);
}
