package listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by Administrator on 2017/5/7.
 */
public class LoginListener implements ServletContextListener{

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("web≥ı ºªØ");
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("webœ˙ªŸ");
    }
}
