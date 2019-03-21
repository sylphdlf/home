package com.dlf;

import com.dlf.web.listeners.ContextInitListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpServletRequest;


/**
 * Created by Administrator on 2017/4/28.
 */
@SpringBootApplication
@EnableScheduling
public class Application extends SpringBootServletInitializer {

    protected static Logger logger = LoggerFactory.getLogger(Application.class);
    @Value("${upload.file.maxsize}")
    private String uploadMaxSize;
    @Value("${request.file.maxsize}")
    private String requestMaxSize;

    @RequestMapping("/")
    public String greeting(HttpServletRequest request, Model model) {
        model.addAttribute("1", "2");
        return "/index";
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.addListeners(new ContextInitListener());
        app.run(args);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //允许上传的文件最大值
        factory.setMaxFileSize(uploadMaxSize); //KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize(requestMaxSize);
        return factory.createMultipartConfig();
    }

}
