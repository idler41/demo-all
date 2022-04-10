package com.lfx.demo.convert;

import com.lfx.demo.domain.SysRole;
import com.lfx.demo.domain.SysRoleExcel;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * @author <a href="mailto:idler41@163.com">linfuxin</a> created by 2022-04-09 20:01:47
 */
public interface WorkerConvert {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    @Mapping(target = "updateBy", qualifiedByName = "operator")
    @Mapping(target = "updateTime", qualifiedByName = "operateTime")
    void updateEntity(@MappingTarget SysRole sysRole, SysRoleExcel e);
}
