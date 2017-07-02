package com.dlf.user.business.observers;

import com.dlf.user.business.utils.MailSendUtils;
import org.springframework.stereotype.Service;

import java.util.Observable;

/**
 * Created by Administrator on 2017/5/29.
 */
@Service
public class MessageObserver extends Observable{

    public void messagePush(String content){
        setChanged();
        this.notifyObservers(content);
    }

    public static void main(String[] args) {
        MessageObserver messageObserver = new MessageObserver();
        MailSendUtils mailSendUtils = new MailSendUtils(messageObserver);
        messageObserver.messagePush("hehehh");
    }
}
