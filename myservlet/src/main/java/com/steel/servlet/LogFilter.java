package com.steel.servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author gang.qin
 * @date 2018-11-01.
 */
public class LogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String site = filterConfig.getInitParameter("site");
        System.out.println("网站名称：" + site);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            HttpServletRequest req = (HttpServletRequest) request;
            ServletResponse resp = (ServletResponse) response;
            System.out.println("网站地址：http://www.google.com " + req.getRequestURI());
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
