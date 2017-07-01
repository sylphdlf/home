package com.dlf.user.web;
import com.dlf.user.web.listeners.ContextInitListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by Administrator on 2017/4/28.
 */
@Configuration
@SpringBootApplication
@RestController
public class Application {

    protected static Logger logger = LoggerFactory.getLogger(Application.class);

    @RequestMapping("/")
    public String greeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.addListeners(new ContextInitListener());
        app.run(args);
        logger.debug("1213131313");
    }
}
