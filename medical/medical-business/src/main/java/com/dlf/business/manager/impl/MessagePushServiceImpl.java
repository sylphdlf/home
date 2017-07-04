package com.dlf.business.manager.impl;

import com.dlf.business.observers.MessageObserver;
import com.dlf.business.manager.MessagePushService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2017/5/29.
 */
public class MessagePushServiceImpl implements MessagePushService {

    @Autowired
    MessageObserver messageObserver;

    public Object messagePush(String content) {
        messageObserver.messagePush(content);
        return "";
    }
}
