package com.lfx.demo.scheduler;

import com.lfx.demo.scheduler.context.LoopContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:idler41@163.con">linfuxin</a> created on 2023-03-31 08:56:04
 */
@Component
@Slf4j
public class LoopManager {

    /**
     * 为避免重复请求，key为String(参数拼接)
     */
    private final ConcurrentHashMap<String, LoopContext> requestMap = new ConcurrentHashMap<>();

    private final long expireTime = TimeUnit.MINUTES.toMillis(1);

    public void addRequest(LoopContext context) {
        requestMap.put(context.getKey(), context);
    }

    @Scheduled(fixedRateString = "${loop.fixedRate:100}")
    public void run() {
        // 删除过期请求
        long now = System.currentTimeMillis();
        Set<LoopContext> expireContexts = requestMap.values().stream()
                .filter(i -> now - i.getRequestTime() > expireTime)
                .collect(Collectors.toSet());
        for (LoopContext ctx : expireContexts) {
            requestMap.remove(ctx.getKey());
        }

        // 执行请求业务
        Map<String, ?> dbDataMap = mockQuery(requestMap.values());
        for (String key : dbDataMap.keySet()) {
            requestMap.get(key).getFuture().complete(dbDataMap.get(key));
            requestMap.remove(key);
        }

        if (log.isDebugEnabled()) {
            log.debug("removeSize={},requestMapSize={}", (expireContexts.size() + dbDataMap.size()), requestMap.size());
        }
    }

    private Map<String, String> mockQuery(Collection<LoopContext> contexts) {
        Map<String, String> result = new HashMap<>(contexts.size());
        for (LoopContext ctx : contexts) {
            boolean success = ThreadLocalRandom.current().nextInt(4) == 2;
            if (success) {
                result.put(ctx.getKey(), String.valueOf(System.currentTimeMillis()));
            }
            log.info("db查询数据:key={},result={}", ctx.getKey(), success);
        }
        // 模拟查询结果
        return result;
    }
}
