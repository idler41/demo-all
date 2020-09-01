package com.lfx.demo.spring.interceptor;

import com.lfx.demo.generator.TraceIdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.lfx.demo.constants.TraceConstants.TRACE_KEY;

/**
 * @author <a href="mailto:linfx@dydf.cn">linfuxin</a>
 * @date 2020-08-28 15:09:43
 */
@Slf4j
public class TraceInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 请求转发后，traceId会重置
        if (handler instanceof HandlerMethod && needGenerate(request)) {
            String traceId = TraceIdGenerator.generate();
            request.setAttribute(TRACE_KEY, traceId);
            MDC.put(TRACE_KEY, traceId);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (handler instanceof HandlerMethod) {
            MDC.remove(TRACE_KEY);
        }
    }


    private boolean needGenerate(HttpServletRequest request) {
        // 新的请求或者请求转发，不需要重新生成traceId
        return request.getAttribute(TRACE_KEY) == null || !request.getAttribute(TRACE_KEY).equals(MDC.get(TRACE_KEY));
    }
}
