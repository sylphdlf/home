package com.dlf.user.business.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import javax.sound.midi.Soundbank;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * Created by Administrator on 2017/5/27.
 */
public class MailSendUtils implements Observer{

    Observable observable;

    public MailSendUtils(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    public void update(Observable o, Object arg) {
        System.out.println(o.getClass());
        System.out.println("hahah");
    }


}
