package com.dlf.web.listeners;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.ServletRequestHandledEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2017/5/7.
 */
//@Component
public class ContextInitListener implements ApplicationListener{

    private Logger logger = LoggerFactory.getLogger(ContextInitListener.class);

    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if(applicationEvent.getClass().equals(ServletRequestHandledEvent.class)){
            ServletRequestHandledEvent event = (ServletRequestHandledEvent)applicationEvent;
            logger.info(event.getRequestUrl());
        }
    }
}
