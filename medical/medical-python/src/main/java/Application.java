import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by Administrator on 2017/4/28.
 */
@SpringBootApplication
@RestController
@EnableWebMvc
@ComponentScan({"com.dlf.*"})
@EnableScheduling
public class Application {

    protected static Logger logger = LoggerFactory.getLogger(Application.class);

    @RequestMapping("/")
    @ResponseBody
    public String greeting(HttpServletRequest request, Model model) {
        model.addAttribute("1", "2");
        return "HelloWorld";
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.run(args);
        logger.debug("python app started");
    }
}
