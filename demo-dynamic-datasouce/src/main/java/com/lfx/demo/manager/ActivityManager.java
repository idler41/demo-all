package com.lfx.demo.manager;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lfx.demo.entity.Activity;
import com.lfx.demo.mapper.ActivityMapper;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * @author <a href="mailto:idler41@163.con">idler41</a>
 * @date 2020-12-31 15:11:49
 */
@Service
public class ActivityManager extends ServiceImpl<ActivityMapper, Activity> {

    @DS("slave_1")
    @Override
    public Activity getById(Serializable id) {
        return super.getById(id);
    }

    @DS("slave")
    @Override
    public List<Activity> list() {
        return super.list();
    }
}