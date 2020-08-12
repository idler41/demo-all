package com.lfx.demo.manager;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.CachePenetrationProtect;
import com.alicp.jetcache.anno.CacheRefresh;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lfx.demo.constant.CacheConstants;
import com.lfx.demo.entity.ActTask;
import com.lfx.demo.mapper.ActTaskMapper;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:linfx@dydf.cn">linfuxin</a>
 * @date 2020-07-16 18:54:04
 */
@Service
public class ActTaskManager extends ServiceImpl<ActTaskMapper, ActTask> {

    @Cached(name = CacheConstants.TaskCache.CACHE_NAME, key = "#id", cacheType = CacheType.BOTH, expire = 7200, localExpire = 5)
    @CacheRefresh(refresh = 1, stopRefreshAfterLastAccess = 24, timeUnit = TimeUnit.HOURS)
    @CachePenetrationProtect
    @Override
    public ActTask getById(Serializable id) {
        return super.getById(id);
    }

    @Cached(name = CacheConstants.TaskCache.CACHE_NAME, key = "#ids", cacheType = CacheType.BOTH, expire = 7200, localExpire = 5)
    public List<ActTask> selectByIds(List<Integer> ids) {
        List<ActTask> result = new ArrayList<>(ids.size());
        for (Integer id : ids) {
            result.add(getById(id));
        }
        return result;
    }

    @CacheInvalidate(name = CacheConstants.TaskCache.CACHE_NAME, key = "#entity.id", condition = "#result")
    @Override
    public boolean updateById(ActTask entity) {
        return super.updateById(entity);
    }
}