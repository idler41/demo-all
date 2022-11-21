package com.lfx.demo.decorator.generic;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;


/**
 * @author linfuxin Created on 2022-02-14 11:21:00
 */
public interface BaseConvert<T, V, Q> {
    /**
     * 查询参数转换
     *
     * @param q 前端参数
     * @return 业务封装对象
     */
    @Named("queryToEntity")
    T queryToEntity(Q q);

    /**
     * 查询结果转换
     *
     * @param t db查询对象
     * @return 前端展示对象
     */
    @Named("entityToVo")
    V entityToVo(T t);

    /**
     * 查询结果集合转换
     *
     * @param list db查询对象
     * @return 前端展示对象
     */
    @IterableMapping(qualifiedByName = "entityToVo")
    List<V> entityToVoList(List<T> list);

    /**
     * 更新参数转换
     *
     * @param v 前端参数
     * @return 业务封装对象
     */
    @Named("voToEntity")
    T voToEntity(V v);

    /**
     * 覆盖更新参数转换
     *
     * @param v 前端参数
     * @param t 实体对象
     */
    @Named("voToEntityOverride")
    @InheritConfiguration(name = "voToEntityIgnoreId")
    void voToEntityOverride(V v, @MappingTarget T t);

    /**
     * 添加参数转换
     *
     * @param v 前端参数对象
     * @return 数据对象
     */
    @Named("voToEntityIgnoreId")
    @Mapping(target = "id", ignore = true)
    T voToEntityIgnoreId(V v);

    /**
     * 更新数据集合参数转换
     *
     * @param v 前端参数
     * @return 业务封装对象
     */
    @IterableMapping(qualifiedByName = "voToEntity")
    List<T> voToEntityList(List<V> v);

    /**
     * 添加数据集合参数转换
     *
     * @param v 前端参数对象
     * @return 数据对象
     */
    @IterableMapping(qualifiedByName = "voToEntityIgnoreId")
    List<T> voToEntityIgnoreIdList(List<V> v);

}