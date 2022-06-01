package com.lfx.demo.abstracts;

import com.lfx.demo.abstracts.convert.AbstractConvert;
import com.lfx.demo.abstracts.domain.SysUserRole;
import com.lfx.demo.abstracts.domain.SysUserRoleExcel;
import com.lfx.demo.abstracts.domain.SysUserRoleVo;
import org.mapstruct.Mapper;

/**
 * @author <a href="mailto:idler41@163.com">linfuxin</a> created by 2022-06-01 21:56:15
 */
@Mapper
public abstract class SysUserRoleConvert extends AbstractConvert<SysUserRole, SysUserRoleVo, SysUserRoleExcel> {
}
