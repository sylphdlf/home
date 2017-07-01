package com.dlf.user.business.manager.impl;

import com.dlf.user.business.manager.MessagePushService;
import com.dlf.user.business.observers.MessageObserver;
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
