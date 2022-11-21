package com.lfx.demo.decorator;

import com.lfx.demo.abstracts.domain.SysRole;
import com.lfx.demo.abstracts.domain.SysRoleVo;

/**
 * @author <a href="mailto:idler41@163.con">linfuxin</a> created on 2022-11-14 11:37:16
 */
public abstract class BaseDomainDecorator implements SysRoleConvert {
    private final SysRoleConvert delegate;

    public BaseDomainDecorator(SysRoleConvert delegate) {
        this.delegate = delegate;
    }

    @Override
    public SysRole voToEntityIgnoreId(SysRoleVo v) {
        SysRole sysRole = delegate.voToEntityIgnoreId(v);
        sysRole.setCode("decorator test");
        return sysRole;
    }

//    @Override
//    public SysRole voToEntity(SysRoleVo v) {
//        SysRole sysRole = delegate.voToEntityIgnoreId(v);
//        sysRole.setCode("decorator test");
//        return sysRole;
//    }
}
