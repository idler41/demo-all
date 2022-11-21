package com.lfx.demo.decorator.generic;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author <a href="mailto:idler41@163.con">idler41</a> created on 2022-05-01 10:06:14
 */
@Mapper
@DecoratedWith(BaseDomainDecorator.class)
public interface SysUserConvert extends BaseConvert<SysUser, SysUserVo, SysUserQuery> {

    public static SysUserConvert INSTANCE = Mappers.getMapper(SysUserConvert.class);
}