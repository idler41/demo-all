package com.lfx.demo.extend.convert;

import com.lfx.demo.extend.domain.SysRole;
import com.lfx.demo.extend.domain.SysRoleExcel;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.time.LocalDateTime;

/**
 * @author <a href="mailto:idler41@163.com">linfuxin</a> created by 2022-04-09 15:03:55
 */
public interface BaseConvert {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    @Mapping(target = "updateBy", expression = "java(operator())")
    @Mapping(target = "updateTime", expression = "java(operateTime())")
    void updateEntity(@MappingTarget SysRole sysRole, SysRoleExcel e);

    default Integer operator() {
        return null;
    }

    default LocalDateTime operateTime() {
        return null;
    }
}
