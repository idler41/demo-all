package com.lfx.demo.extend.convert;

import com.lfx.demo.extend.convert.worker.OperateWorker;
import com.lfx.demo.extend.domain.SysRole;
import com.lfx.demo.extend.domain.SysRoleExcel;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

/**
 * @author <a href="mailto:idler41@163.com">linfuxin</a> created by 2022-04-09 15:14:51
 */
@Mapper(imports = OperateWorker.class)
public interface SysRoleWorkerConvert extends WorkerConvert{

    SysRoleWorkerConvert INSTANCE = Mappers.getMapper(SysRoleWorkerConvert.class);


}
