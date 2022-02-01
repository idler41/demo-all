package com.lfx.demo.mapper;

import com.lfx.demo.domain.SysDictType;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * @author <a href="mailto:idler41@163.con">idler41</a> created on 2022-01-31 21:14:07
 */
@Repository
public interface SysDictTypeMapper  {
    SysDictType selectById(Serializable var1);
}