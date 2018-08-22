package com.dlf.web.interceptor;

import com.dlf.business.anno.UrlPermissionIgnoreAnno;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;

@Configuration
public class UrlInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //根据注解判断url是否放行
        HandlerMethod handlerMethod = (HandlerMethod)o;
        UrlPermissionIgnoreAnno anno = handlerMethod.getMethodAnnotation(UrlPermissionIgnoreAnno.class);
        if(null != anno){
            return true;
        }
        //获取链接，检查权限
        Subject subject = SecurityUtils.getSubject();
        try {
            //管理员有全部权限
            subject.checkRole("admin");
        }catch (Exception e){
            subject.checkPermission(httpServletRequest.getServletPath());
        }
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
//        System.out.println("post");
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
//        System.out.println("after");
    }
}
