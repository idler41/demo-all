package com.lfx.demo.decorator;

import com.lfx.demo.abstracts.domain.SysRole;
import com.lfx.demo.abstracts.domain.SysRoleVo;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

/**
 * @author <a href="mailto:idler41@163.con">linfuxin</a> created on 2022-11-14 11:31:18
 */
@Mapper
@DecoratedWith(BaseDomainDecorator.class)
public interface SysRoleConvert {
    SysRoleConvert INSTANCE = Mappers.getMapper(SysRoleConvert.class);
    @Named("voToEntityIgnoreId")
    @Mapping(target = "id", ignore = true)
    SysRole voToEntityIgnoreId(SysRoleVo v);

    @Named("voToEntity")
    SysRole voToEntity(SysRoleVo v);
}
