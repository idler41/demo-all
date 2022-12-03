package com.lfx.demo.manager;

import com.lfx.demo.annotation.DataScope;
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
        return this.mapper.get(id);
    }

    public SysDictType selectById(Serializable id) {
        return this.mapper.selectById(id);
    }

    public SysDictType getByDictLabel(String dictLabel) {
        return this.mapper.selectByLabel(dictLabel, null, null);
    }

    public SysDictType getByDictLabel(SysDictType sysDictType) {
        return this.mapper.selectByEntity2(sysDictType);
    }

    public SysDictType getByDictLabelCollection(String[] labels) {
        return this.mapper.selectByLabelCollection(labels, null);
    }

    public SysDictType select5() {
        return mapper.select5();
    }

    public SysDictType select6(Integer id) {
        return mapper.select6(id);
    }
}