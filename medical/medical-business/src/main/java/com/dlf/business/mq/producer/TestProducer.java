//package com.dlf.business.mq.producer;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//
///**
// * description
// * Date: 2017/12/5
// *
// * @author dailf@zendaimoney.com
// */
//@Service
//public class TestProducer {
//    private Logger logger = LoggerFactory.getLogger(TestProducer.class);
//
//    @Autowired
//    private AmqpTemplate rabbitTemplate;
//
//    public void send() {
//        String context = "hello1 " + new Date();
//        System.out.println("Sender : " + context);
//        this.rabbitTemplate.convertAndSend("amp.topic", "topic.t", context);
//    }
//}
