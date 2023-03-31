package com.lfx.demo.scheduler.context;

import lombok.Data;

import java.util.concurrent.CompletableFuture;

/**
 * @author <a href="mailto:idler41@163.con">linfuxin</a> created on 2023-03-31 08:57:42
 */
@Data
public class LoopContext {

    private CompletableFuture<Object> future;

    private LoopParam loopParam;

    private String key;
    private boolean blobResult;
    private long requestTime;
}
