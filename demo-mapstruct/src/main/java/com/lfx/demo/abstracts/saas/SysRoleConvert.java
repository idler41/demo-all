package com.lfx.demo.abstracts.saas;

import com.lfx.demo.abstracts.saas.convert.AbstractBaseDomainConvert;
import com.lfx.demo.abstracts.saas.domain.SysRole;
import com.lfx.demo.abstracts.saas.domain.SysRoleExcel;
import com.lfx.demo.abstracts.saas.domain.SysRoleVo;
import org.mapstruct.Mapper;

/**
 * @author <a href="mailto:idler41@163.com">linfuxin</a> created by 2022-06-01 21:56:15
 */
@Mapper
public abstract class SysRoleConvert extends AbstractBaseDomainConvert<SysRole, SysRoleVo, SysRoleExcel> {
}
