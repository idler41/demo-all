package com.lfx.demo.manager;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lfx.demo.entity.ActTask;
import com.lfx.demo.cache.CacheConstants;
import com.lfx.demo.mapper.ActTaskMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @author <a href="mailto:linfx@dydf.cn">linfuxin</a>
 * @date 2020-07-16 18:54:04
 */
@Service
public class ActTaskManager extends ServiceImpl<ActTaskMapper, ActTask> {

    @Cacheable(value = CacheConstants.TaskCache.CACHE_NAME, key = "T(com.lfx.demo.cache.CacheConstants.TaskCache).KEY_TASK_ID + #id", unless = "#result==null")
    @Override
    public ActTask getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    public boolean updateById(ActTask entity) {
        return super.updateById(entity);
    }
}