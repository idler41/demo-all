package com.lfx.demo.dubbo.filter;

import com.lfx.demo.generator.TraceIdGenerator;
import com.lfx.demo.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.monitor.MonitorService;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.RpcException;

/**
 * @author <a href="mailto:linfx@dydf.cn">linfuxin</a>
 * @date 2020-08-18 15:14:45
 */
@Slf4j
public abstract class AbstractTraceFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        if (isExcludeTrace(invoker)) {
            return invoker.invoke(invocation);
        }
        try {
            traceBeforeInvoke(invoker, invocation);
            Result result = invoker.invoke(invocation);
            traceAfterInvoke(invoker, invocation, result);
            return result;
        } finally {
            traceCompleteInvoke(invoker, invocation);
        }
    }

    protected abstract void traceCompleteInvoke(Invoker<?> invoker, Invocation invocation);

    protected abstract void traceBeforeInvoke(Invoker<?> invoker, Invocation invocation);

    protected abstract void traceAfterInvoke(Invoker<?> invoker, Invocation invocation, Result result);

    protected boolean isExcludeTrace(Invoker<?> invoker) {
        return invoker.getInterface() == MonitorService.class;
    }

    protected String generateTraceId(Invoker<?> invoker, Invocation invocation) {
        String traceId = TraceIdGenerator.generate();
        String className = invoker.getInterface() != null ? invoker.getInterface().getName() : null;
        log.warn("traceId is empty, create traceId:[{}], className:[{}], method:[{}]", traceId, className, invocation.getMethodName());
        return traceId;
    }
}
