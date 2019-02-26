//package com.dlf.business.manager.kafka.impl;
//
//import com.dlf.business.manager.kafka.KafkaService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class KafkaServiceImpl implements KafkaService {
//
//    @Autowired
//    KafkaTemplate kafkaTemplate;
//
//    @Override
//    public void send(String topic, String key, String value) {
//        kafkaTemplate.send("userAccessLogs", value);
//    }
//}
