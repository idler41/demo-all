package com.lfx.demo.filter;

import com.lfx.demo.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcContext;
import org.slf4j.MDC;

import static org.apache.dubbo.common.constants.CommonConstants.CONSUMER;

/**
 * @author <a href="mailto:linfx@dydf.cn">linfuxin</a>
 * @date 2020-08-18 15:57:32
 */
@Slf4j
@Activate(group = CONSUMER, order = -10000)
public class ConsumerTraceFilter extends AbstractTraceFilter {
    @Override
    protected void traceCompleteInvoke(Invoker<?> invoker, Invocation invocation) {
        MDC.remove(traceIdKey);
    }

    @Override
    protected void traceBeforeInvoke(Invoker<?> invoker, Invocation invocation) {
        String traceId = MDC.get(traceIdKey);
        if (StringUtil.isBlank(traceId)) {
            traceId = generateTraceId(invoker, invocation);
            RpcContext.getContext().setAttachment(traceIdKey, traceId);
            MDC.put(traceIdKey, traceId);
        }
    }

    @Override
    protected void traceAfterInvoke(Invoker<?> invoker, Invocation invocation, Result result) {

    }
}
