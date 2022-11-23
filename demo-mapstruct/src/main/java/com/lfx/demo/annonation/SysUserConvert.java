package com.lfx.demo.annonation;

import com.lfx.demo.domain.SysUser;
import com.lfx.demo.domain.SysUserQuery;
import com.lfx.demo.domain.SysUserVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author <a href="mailto:idler41@163.con">idler41</a> created on 2022-05-01 10:06:14
 */
@Mapper(imports = {OperateExpressionConvert.class})
//@Mapper(uses = {OperateNameConvert.class})
//@Mapper(uses = OperateAnnotationConvert.class)
public interface SysUserConvert extends BaseDomainConvert<SysUser, SysUserVo, SysUserQuery> {

    public static SysUserConvert INSTANCE = Mappers.getMapper(SysUserConvert.class);

}