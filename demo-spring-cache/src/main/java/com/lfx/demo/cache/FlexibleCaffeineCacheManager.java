package com.lfx.demo.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.caffeine.CaffeineCacheManager;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:linfx@dydf.cn">linfuxin</a>
 * @date 2020-07-28 15:42:14
 */
@Slf4j
public class FlexibleCaffeineCacheManager extends CaffeineCacheManager implements InitializingBean {

    private final Map<String, String> cacheSpecs;

    private final Map<String, Caffeine<Object, Object>> builders = new HashMap<>();

    public FlexibleCaffeineCacheManager(Map<String, String> cacheSpecs) {
        this.cacheSpecs = cacheSpecs;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (cacheSpecs != null) {
            for (Map.Entry<String, String> cacheSpecEntry : cacheSpecs.entrySet()) {
                builders.put(cacheSpecEntry.getKey(), Caffeine.from(cacheSpecEntry.getValue()));
            }
        }
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected Cache<Object, Object> createNativeCaffeineCache(String name) {
        Caffeine<Object, Object> builder = builders.get(name);
        return builder == null ? super.createNativeCaffeineCache(name) : builder.build();
    }
}
