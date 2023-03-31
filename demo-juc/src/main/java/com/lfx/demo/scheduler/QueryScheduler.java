package com.lfx.demo.scheduler;

import com.lfx.demo.scheduler.context.LoopContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:idler41@163.con">linfuxin</a> created on 2023-03-31 08:56:04
 */
@Component
@Slf4j
public class QueryScheduler {

    /**
     * key重复将导致部分线程不能唤醒
     */
    private final ConcurrentHashMap<LoopContext, CompletableFuture<Object>> requestMap = new ConcurrentHashMap<>();

    private final long expireTime = TimeUnit.MINUTES.toMillis(1);

    public void addRequest(LoopContext context) {
        requestMap.put(context, context.getFuture());
    }

    @Scheduled(fixedRateString = "${loop.fixedRate:100}")
    public void run() {
        // 删除过期请求
        long now = System.currentTimeMillis();
        Set<LoopContext> expireContexts = requestMap.keySet().stream()
                .filter(i -> now - i.getRequestTime() > expireTime)
                .collect(Collectors.toSet());
        for (LoopContext ctx : expireContexts) {
            requestMap.get(ctx).complete(null);
            requestMap.remove(ctx);
        }

        // 执行请求业务
        Map<LoopContext, Object> dbDataMap = mockQuery(requestMap.keySet());
        for (LoopContext key : dbDataMap.keySet()) {
            requestMap.get(key).complete(dbDataMap.get(key));
            requestMap.remove(key);
        }

        if (log.isDebugEnabled()) {
            log.debug("removeSize={},requestMapSize={}", (expireContexts.size() + dbDataMap.size()), requestMap.size());
        }
    }

    private Map<LoopContext, Object> mockQuery(Collection<LoopContext> contexts) {
        Map<LoopContext, Object> result = new HashMap<>(contexts.size());
        for (LoopContext ctx : contexts) {
            boolean success = ThreadLocalRandom.current().nextInt(4) == 2;
            if (success) {
                result.put(ctx, System.nanoTime());
                log.info("db查询数据:key={},result={}", ctx.getKey(), result.get(ctx));
            }
        }
        // 模拟查询结果
        return result;
    }
}
