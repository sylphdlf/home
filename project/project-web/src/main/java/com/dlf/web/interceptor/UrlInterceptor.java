package com.dlf.web.interceptor;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;

@Configuration
public class UrlInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("pre");
        System.out.println(httpServletRequest.getServletPath());
        Subject subject = SecurityUtils.getSubject();
        subject.checkPermission(httpServletRequest.getServletPath());
//        subject.checkPermission(httpServletRequest.getServletPath());
//        System.out.println(subject.getSession().getId());
        System.out.println(httpServletRequest.getSession().getId());
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("post");
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("after");
    }
}
