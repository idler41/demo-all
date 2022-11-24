package com.lfx.demo.inherit;

import com.lfx.demo.abstracts.domain.SysRole;
import com.lfx.demo.abstracts.domain.SysRoleVo2;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

/**
 * @author <a href="mailto:idler41@163.con">linfuxin</a> created on 2022-11-14 11:20:39
 */
@Mapper
public interface SysRoleConvert {
    SysRoleConvert INSTANCE = Mappers.getMapper(SysRoleConvert.class);

    /**
     * 查询结果转换
     *
     * @param t db查询对象
     * @return 前端展示对象
     */
    @Named("entityToVo")
    SysRoleVo2 entityToVo(SysRole t);

    @Named("voToEntityOverride")
    @InheritConfiguration(name = "voToEntity")
    void voToEntityOverride(SysRoleVo2 v, @MappingTarget SysRole t);

    @Named("voToEntityIgnoreId")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "code", source = "code2")
    SysRole voToEntityIgnoreId(SysRoleVo2 v);

    @Named("voToEntity")
    @Mapping(target = "code", source = "code2")
    SysRole voToEntity(SysRoleVo2 v);
}
