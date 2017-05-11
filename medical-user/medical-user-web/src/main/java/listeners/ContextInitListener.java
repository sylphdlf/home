package listeners;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.web.context.support.ServletRequestHandledEvent;

/**
 * Created by Administrator on 2017/5/7.
 */
public class ContextInitListener implements ApplicationListener{

    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if(applicationEvent.getClass().equals(ServletRequestHandledEvent.class)){
            System.out.println("web���ʼ�����");
        }
    }
}
