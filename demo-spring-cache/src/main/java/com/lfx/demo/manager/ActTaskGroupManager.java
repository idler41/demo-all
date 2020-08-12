package com.lfx.demo.manager;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lfx.demo.entity.ActTask;
import com.lfx.demo.entity.ActTaskGroup;
import com.lfx.demo.cache.CacheConstants;
import com.lfx.demo.mapper.ActTaskGroupMapper;
import com.lfx.demo.mapper.ActTaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * @author <a href="mailto:linfx@dydf.cn">linfuxin</a>
 * @date 2020-07-16 18:54:04
 */
@Service
public class ActTaskGroupManager extends ServiceImpl<ActTaskGroupMapper, ActTaskGroup> {

    @Autowired
    private ActTaskMapper actTaskMapper;

    @Cacheable(value = CacheConstants.TaskGroupCache.CACHE_NAME, key = "T(com.lfx.demo.cache.CacheConstants.TaskGroupCache).KEY_GROUP_ID + #id", unless = "#result==null")
    @Override
    public ActTaskGroup getById(Serializable id) {
        return super.getById(id);
    }

    @CacheEvict(value = CacheConstants.TaskGroupCache.CACHE_NAME, key = "T(com.lfx.demo.cache.CacheConstants.TaskGroupCache).KEY_GROUP_ID + #entity.id")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateById(ActTaskGroup entity) {
        return super.updateById(entity);
    }

    @Caching(evict = {
            @CacheEvict(value = CacheConstants.TaskGroupCache.CACHE_NAME, key = "T(com.lfx.demo.cache.CacheConstants.TaskGroupCache).KEY_GROUP_ID + #actTaskGroup.id"),
            @CacheEvict(value = CacheConstants.TaskCache.CACHE_NAME, allEntries = true),
    })
    @Transactional(rollbackFor = Exception.class)
    public void updateGroupAndTaskList(ActTaskGroup actTaskGroup, List<ActTask> actTaskList) {
        baseMapper.updateById(actTaskGroup);
        for (ActTask item : actTaskList) {
            actTaskMapper.updateById(item);
        }
    }
}