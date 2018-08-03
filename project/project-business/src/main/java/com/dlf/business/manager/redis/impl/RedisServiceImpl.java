package com.dlf.business.manager.redis.impl;

import com.dlf.business.manager.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisTemplate redisTemplate;
    //max retry time
    private static final int MAX_RETRY_TIME = 3;

    public void put(String key, String value) {
        int time = 0;
        boolean flag;
        do{
            time ++;
            try {
                ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
                valueOperations.set(key, value);
                flag = true;
            }catch (Throwable e){
                flag = false;
                //FIXME
                if(time == 1){
                    //发送邮件
                }
            }
        }while (!flag && time < MAX_RETRY_TIME);
    }

    public void put(String key, Object value) {
        int time = 0;
        boolean flag;
        do{
            try {
                redisTemplate.setKeySerializer(new StringRedisSerializer());
                redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
                ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
                valueOperations.set(key, value);
                flag = true;
            }catch (Throwable e){
                flag = false;
            }
        }while (!flag && time < MAX_RETRY_TIME);
    }

    public void put(String key, String value, Long timeoutSeconds) {
        int time = 0;
        boolean flag;
        do{
            time ++;
            try {
                ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
                valueOperations.set(key, value, TimeUnit.SECONDS.toSeconds(timeoutSeconds), TimeUnit.SECONDS);
                flag = true;
            }catch (Throwable e){
                flag = false;
                //FIXME
                if(time == 1){
                    //发送邮件
                }
            }
        }while (!flag && time < MAX_RETRY_TIME);
    }

    public String get(String key) {
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    public Object getObj(String key) {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    @Override
    public void removeKey(String key) {
        redisTemplate.delete(key);
    }
}
