//package com.WPsports.security;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//@Component
//@Slf4j
//public class loginFilter implements Filter {
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest httpServletRequest=(HttpServletRequest)request;
//        HttpSession session=httpServletRequest.getSession(false);
//        if(session==null){
//            HttpServletResponse httpServletResponse=(HttpServletResponse)response;
//            httpServletResponse.sendRedirect("/nomember");
//        }
//        else {
//            chain.doFilter(request,response);
//        }
//
//    }
//
//}

//공도형 작업용 주석처리(로그인 권한)