package com.lfx.demo.weblog.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author <a href="mailto:linfx@dydf.cn">linfuxin</a>
 * @date 2020-08-28 10:20:49
 */
@WebFilter(filterName = "TokenFilter")
@Slf4j
public class TokenFilter implements Filter {

    @Override
    public void destroy() {
        log.info("销毁 TokenFilter");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("初始化 TokenFilter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token = request.getHeader("token");
        if (log.isDebugEnabled()) {
            log.debug("path:{},token:{}", request.getServletPath(), token);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
