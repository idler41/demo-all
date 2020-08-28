package com.lfx.demo.dubbo.filter;

import com.lfx.demo.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcContext;
import org.slf4j.MDC;

import static com.lfx.demo.constants.TraceConstants.TEMP_TRACE_KEY;
import static com.lfx.demo.constants.TraceConstants.TRACE_KEY;
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
        if (!StringUtil.isBlank(MDC.get(TEMP_TRACE_KEY))) {
            MDC.remove(TRACE_KEY);
            MDC.remove(TEMP_TRACE_KEY);
        }
    }

    @Override
    protected void traceBeforeInvoke(Invoker<?> invoker, Invocation invocation) {
        String traceId = MDC.get(TRACE_KEY);
        if (StringUtil.isBlank(traceId)) {
            // 一般不应该进入该方法
            traceId = generateTraceId(invoker, invocation);
            MDC.put(TRACE_KEY, traceId);
            MDC.put(TEMP_TRACE_KEY, traceId);
        }
        RpcContext.getContext().setAttachment(TRACE_KEY, traceId);
    }

    @Override
    protected void traceAfterInvoke(Invoker<?> invoker, Invocation invocation, Result result) {

    }
}
