//package com.dlf.business.mq.consumer;
//
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
///**
// * description
// * Date: 2017/12/5
// *
// * @author dailf@zendaimoney.com
// */
//@Component
//@RabbitListener(queues = "topic.t2")
//public class TestConsumer2 {
//    @RabbitHandler
//    public void process(String hello) {
//        System.out.println("Receiver2  : " + hello);
//    }
//}
