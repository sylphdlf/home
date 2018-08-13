package com.dlf.config;

import com.dlf.web.interceptor.UrlInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UrlInterceptor());
        System.out.println("===========   url拦截器注册完毕   ===========");
    }
}
