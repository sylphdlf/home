package com.dlf.user.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/5/21.
 */
@RestController
public class StringRedisController {
    protected static Logger logger= LoggerFactory.getLogger(StringRedisController.class);

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Resource(name = "stringRedisTemplate")
    ValueOperations<String,String> valueOperations;

    @RequestMapping("setKeyAndValue")
    public String setKeyAndValue(String key,String value){
        logger.debug("访问set:key={},value={}",key,value);
        valueOperations.set(key, value);
        return "Set Ok";
    }

    @RequestMapping("/getKey")
    public String getKey(String key){
        logger.debug("key============="+key);
        String value = valueOperations.get(key);
        logger.debug("value==========="+value);
        return value;
    }
}
