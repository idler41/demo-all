package com.lfx.demo.dubbo.filter;

import com.lfx.demo.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcContext;
import org.slf4j.MDC;

import static com.lfx.demo.constants.TraceConstants.TRACE_KEY;
import static org.apache.dubbo.common.constants.CommonConstants.PROVIDER;

/**
 * @author <a href="mailto:linfx@dydf.cn">linfuxin</a>
 * @date 2020-08-18 15:57:12
 */
@Slf4j
@Activate(group = PROVIDER, order = -10000)
public class ProviderTraceFilter extends AbstractTraceFilter {
    @Override
    protected void traceCompleteInvoke(Invoker<?> invoker, Invocation invocation) {
        MDC.remove(TRACE_KEY);
    }

    @Override
    protected void traceBeforeInvoke(Invoker<?> invoker, Invocation invocation) {
        String traceId = RpcContext.getContext().getAttachment(TRACE_KEY);
        if (StringUtil.isBlank(traceId)) {
            traceId = generateTraceId(invoker, invocation);
            RpcContext.getContext().setAttachment(TRACE_KEY, traceId);
        }
        MDC.put(TRACE_KEY, traceId);
        log.info("service:{},method:{},arguments:{}", invoker.getInterface().getSimpleName(), invocation.getMethodName(), invocation.getArguments());
    }

    @Override
    protected void traceAfterInvoke(Invoker<?> invoker, Invocation invocation, Result result) {

    }
}
