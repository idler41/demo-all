package com.lfx.demo.uses;

import com.lfx.demo.abstracts.domain.SysRole;
import com.lfx.demo.abstracts.domain.SysRoleVo3;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

/**
 * @author <a href="mailto:idler41@163.con">idler41</a> created on 2022-05-01 10:06:14
 */
@Mapper(uses = {LocalDateConvert.class})
public interface SysRoleConvert {

    public static SysRoleConvert INSTANCE = Mappers.getMapper(SysRoleConvert.class);

    /**
     * 查询结果转换
     *
     * @param t db查询对象
     * @return 前端展示对象
     */
    @Named("entityToVo")
    SysRoleVo3 entityToVo(SysRole t);

    @Named("voToEntity")
    SysRole voToEntity(SysRoleVo3 v);
}