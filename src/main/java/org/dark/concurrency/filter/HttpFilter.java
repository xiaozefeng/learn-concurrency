package org.dark.concurrency.filter;

import lombok.extern.slf4j.Slf4j;
import org.dark.concurrency.example.threadLocal.RequestHolder;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 请求过滤器
 * order 表示在多个filter时，过滤的顺序，数字越小越在前面
 *
 * @author xiaozefeng
 * @date 2018/4/21 下午11:46
 */
@Order(1)
@WebFilter(filterName = "httpFilter", urlPatterns = "/*")
@Slf4j
public class HttpFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        long id = Thread.currentThread().getId();
        log.info("{}, {}", id, request.getRequestURI());
        RequestHolder.add(id);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
