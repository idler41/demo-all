package com.lfx.demo.abstracts.saas;

import com.lfx.demo.abstracts.saas.convert.AbstractConvert;
import com.lfx.demo.abstracts.saas.domain.SysUserRole;
import com.lfx.demo.abstracts.saas.domain.SysUserRoleExcel;
import com.lfx.demo.abstracts.saas.domain.SysUserRoleVo;
import org.mapstruct.Mapper;

/**
 * @author <a href="mailto:idler41@163.com">linfuxin</a> created by 2022-06-01 21:56:15
 */
@Mapper
public abstract class SysUserRoleConvert extends AbstractConvert<SysUserRole, SysUserRoleVo, SysUserRoleExcel> {
}
