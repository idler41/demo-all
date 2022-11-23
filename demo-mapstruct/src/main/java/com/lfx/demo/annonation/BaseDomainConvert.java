package com.lfx.demo.annonation;

import com.lfx.demo.domain.BaseConvert;
import com.lfx.demo.domain.BaseDomain;
import com.lfx.demo.domain.SysUser;
import com.lfx.demo.domain.SysUserVo;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

/**
 * @author linfuxin Created on 2022-02-14 11:21:00
 */
public interface BaseDomainConvert<T extends BaseDomain, V, Q> extends BaseConvert<T, V, Q> {

    @Named("voToEntityOverride")
    @InheritConfiguration(name = "voToEntityIgnoreId")
    void voToEntityOverride(V v, @MappingTarget T t);

    /**
     * 更新参数转换
     *
     * @param v 前端参数
     * @return 业务封装对象
     */
    @Named("voToEntity")
    @Mapping(target = "updateBy", expression = "java(OperateExpressionConvert.operator())")
    @Mapping(target = "updateTime", expression = "java(OperateExpressionConvert.operateTime())")
    T voToEntity(V v);

    /**
     * 添加参数转换
     *
     * @param v 前端参数对象
     * @return 数据对象
     */
    @Named("voToEntityIgnoreId")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createBy", expression = "java(OperateExpressionConvert.operator())")
    @Mapping(target = "createTime", expression = "java(OperateExpressionConvert.operateTime())")
    T voToEntityIgnoreId(V v);


//    /**
//     * 更新参数转换
//     *
//     * @param v 前端参数
//     * @return 业务封装对象
//     */
//    @Named("voToEntity")
//    @Mapping(target = "updateBy", qualifiedByName = {"operateNameConvert", "operator"})
//    @Mapping(target = "updateTime", qualifiedByName = {"operateNameConvert", "operateTime"})
//    T voToEntity(V v);
//
//    /**
//     * 添加参数转换
//     *
//     * @param v 前端参数对象
//     * @return 数据对象
//     */
//    @Named("voToEntityIgnoreId")
//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "createBy", qualifiedByName = {"operateNameConvert", "operator"})
//    @Mapping(target = "createTime", qualifiedByName = {"operateNameConvert", "operateTime"})
//    T voToEntityIgnoreId(V v);
//
//    /**
//     * 更新参数转换
//     *
//     * @param v 前端参数
//     * @return 业务封装对象
//     */
//    @Named("voToEntity")
//    @Mapping(target = "updateBy", qualifiedBy = {OperateConvert.class, Operator.class})
//    @Mapping(target = "updateTime", qualifiedBy = {OperateConvert.class, OperateTime.class})
//    T voToEntity(V v);
//
//    /**
//     * 添加参数转换
//     *
//     * @param v 前端参数对象
//     * @return 数据对象
//     */
//    @Named("voToEntityIgnoreId")
//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "createBy", qualifiedBy = {OperateConvert.class, Operator.class})
//    @Mapping(target = "createTime", qualifiedBy = {OperateConvert.class, OperateTime.class})
//    T voToEntityIgnoreId(V v);
}