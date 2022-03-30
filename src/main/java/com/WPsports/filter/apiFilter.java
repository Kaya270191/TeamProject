package com.WPsports.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = {"/test/*","test2/*"})
public class apiFilter implements Filter {

//    필터 인스턴스 초기화
    @Override
    public void init(FilterConfig filterConfig) throws ServletException{
        log.info("필터 인스턴스 초기화");
    }

//    전후처리
//    request,response 가 필터를 거칠 때 수행되는 메소드
//    -chain.doFilter() 기점으로 req,rsp 나눠짐.
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String requestURI = req.getRequestURI();
        log.info("---Request(" + requestURI + ") 필터---");
        chain.doFilter(request, response);
        log.info("---Response(" + requestURI + ") 필터---");
    }

//    필터 인스턴스 종료
    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
