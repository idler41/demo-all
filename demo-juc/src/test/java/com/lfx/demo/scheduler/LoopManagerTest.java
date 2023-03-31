package com.lfx.demo.scheduler;

import com.lfx.demo.AbstractSpringTest;
import com.lfx.demo.scheduler.context.LoopContext;
import com.lfx.demo.scheduler.context.LoopParam;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:idler41@163.con">linfuxin</a> created on 2023-03-31 10:52:16
 */
public class LoopManagerTest extends AbstractSpringTest {

    @Autowired
    private QueryScheduler loopManager;


    @BeforeClass
    public static void setup() {
        initSysProperty();
//        System.setProperty("logging.level.com.lfx.demo", "debug");
    }

    @Test
    public void run() throws InterruptedException {
        int dataSize = 200;

        List<LoopContext> data = buildData(dataSize);
        for (LoopContext item : data) {
            loopManager.addRequest(item);
        }
        data.stream()
                .map(LoopContext::getFuture)
                .collect(Collectors.toList())
                .forEach(i -> System.out.println("接收数据=>" + i.join()));
    }

    private List<LoopContext> buildData(int dataSize) {
        List<LoopContext> result = new ArrayList<>(dataSize);
        for (int i = 0; i < dataSize; i++) {
            LoopParam loopParam = new LoopParam();
            loopParam.setType(String.valueOf(ThreadLocalRandom.current().nextInt(2)));
            loopParam.setParam(String.valueOf(ThreadLocalRandom.current().nextInt(100)));

            LoopContext context = new LoopContext();
            context.setKey(loopParam.getType() + "@" + loopParam.getParam());
            context.setLoopParam(loopParam);
            context.setRequestTime(System.currentTimeMillis());
            CompletableFuture<Object> future = new CompletableFuture<>();
            context.setFuture(future);
            result.add(context);
        }
        return result;
    }
}
