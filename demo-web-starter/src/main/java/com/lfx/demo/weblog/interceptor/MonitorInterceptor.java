package com.lfx.demo.weblog.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author <a href="mailto:linfx@dydf.cn">linfuxin</a>
 * @date 2020-08-28 15:44:48
 */
@Slf4j
public class MonitorInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            StopWatch stopWatch = new StopWatch();
            request.setAttribute("monitor", stopWatch);
            stopWatch.start();
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (handler instanceof HandlerMethod) {
            StopWatch stopWatch = (StopWatch) request.getAttribute("monitor");
            stopWatch.stop();
            log.info("url:{},totalTime:{}ms", request.getServletPath(), stopWatch.getTotalTimeMillis());
            request.removeAttribute("monitor");
        }

    }
}
