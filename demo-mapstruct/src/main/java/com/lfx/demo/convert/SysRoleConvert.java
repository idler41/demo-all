package com.lfx.demo.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author <a href="mailto:idler41@163.com">linfuxin</a> created by 2022-04-09 15:14:51
 */
@Mapper
public interface SysRoleConvert extends BaseConvert{

    SysRoleConvert INSTANCE = Mappers.getMapper(SysRoleConvert.class);
}
