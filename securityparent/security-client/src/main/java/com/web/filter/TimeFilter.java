package com.web.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

/**
 * @author heartccace
 * @create 2020-05-29 10:59
 * @Description 最原始的HttpRequest和HttpResponse
 * 执行时机：filter -> interceptor -> advice
 * @Version 1.0
 */
@Component
public class TimeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("TimeFilter initialized!");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("time filter start");
        long start = new Date().getTime();
        chain.doFilter(request, response);
        System.out.println("time filter 耗时:"+ (new Date().getTime() - start));
        System.out.println("time filter finish");
    }

    @Override
    public void destroy(){
        System.out.println("TimeFilter destroyed");
    }

}