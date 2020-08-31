package com.lfx.demo.dubbo.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;

import static org.apache.dubbo.common.constants.CommonConstants.PROVIDER;

/**
 * @author <a href="mailto:linfx@dydf.cn">linfuxin</a>
 * @date 2020-08-31 11:10:59
 */
@Slf4j
@Activate(group = PROVIDER, order = -9999)
public class ProviderArgsLogFilter extends AbstractTraceFilter {

    @Setter
    private ObjectMapper objectMapper;

    @Override
    protected void traceCompleteInvoke(Invoker<?> invoker, Invocation invocation) {

    }

    @Override
    protected void traceBeforeInvoke(Invoker<?> invoker, Invocation invocation) {
        String params = null;
        try {
            params = objectMapper.writeValueAsString(invocation.getArguments().length == 1 ? invocation.getArguments()[0] : invocation.getArguments());
        } catch (JsonProcessingException e) {
            log.warn("dubbo参数转json出现异常!", e);
        }
        log.info("调用服务:{}#{},参数:{}", invoker.getInterface().getSimpleName(), invocation.getMethodName(), params == null ? invocation.getArguments() : params);
    }

    @Override
    protected void traceAfterInvoke(Invoker<?> invoker, Invocation invocation, Result result) {

    }
}
