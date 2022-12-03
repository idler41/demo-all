package com.lfx.demo.mapper;

import com.lfx.demo.annotation.DataScope;
import com.lfx.demo.domain.SysDictType;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * @author <a href="mailto:idler41@163.con">idler41</a> created on 2022-01-31 21:14:07
 */
@Repository
public interface SysDictTypeMapper extends BaseMapper<SysDictType> {

    @DataScope(requestParam = "id")
    @Override
    SysDictType get(Serializable id);

    @DataScope(requestParam = "id")
    SysDictType selectById(Serializable var1);

    SysDictType selectByLabel(@Param("dictLabel") String dictLabel, @Param("test2") String test2, @Param("") String test3);

    @DataScope
    SysDictType selectByEntity2(SysDictType sysDictType);

    @DataScope(requestParam = "labels")
    SysDictType selectByLabelCollection(String[] labels, String test);

    @DataScope
    SysDictType select5();

    @DataScope(requestParam = "id")
    SysDictType select6(int id);
}