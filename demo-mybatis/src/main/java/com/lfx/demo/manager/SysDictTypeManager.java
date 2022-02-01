package com.lfx.demo.manager;

import com.lfx.demo.domain.SysDictType;
import com.lfx.demo.mapper.SysDictTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @author <a href="mailto:idler41@163.con">idler41</a> created on 2022-01-31 21:14:07
 */
@Service
public class SysDictTypeManager {

    @Autowired
    private SysDictTypeMapper mapper;

    public SysDictType get(Serializable id) {
        return this.mapper.selectById(id);
    }
}