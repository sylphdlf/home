package com.dlf.web;

import com.dlf.web.listeners.ContextInitListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by Administrator on 2017/4/28.
 */
@Configuration
@SpringBootApplication
@RestController
public class Application extends WebMvcConfigurerAdapter{

    protected static Logger logger = LoggerFactory.getLogger(Application.class);

    @RequestMapping("/")
    @ResponseBody
    public String greeting(HttpServletRequest request, Model model) {
        model.addAttribute("1", "2");
        return "HelloWorld";
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.addListeners(new ContextInitListener());
        app.run(args);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static");
    }

}
