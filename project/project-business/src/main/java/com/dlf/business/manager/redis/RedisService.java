package com.dlf.business.manager.redis;

import org.apache.shiro.session.Session;
import org.crazycake.shiro.exception.SerializationException;

import java.util.List;
import java.util.Set;

public interface RedisService {
    /**
     * put
     * @param key
     * @param value
     */
    void put(String key, String value);
    void put(String key, Object value);

    /**
     * put timeout
     * @param key
     * @param value
     * @param timeoutSeconds
     */
    void put(String key, String value, Long timeoutSeconds);
    void put(String key, Object value, Long timeoutSeconds);

    /**
     * StringRedisTemplate
     * @param key
     * @return
     */
    String get(String key);
    Object getObj(String key);

    void removeKey(String key);

    Session getSession(String key);

    Set<String> getKeysByPrefix(String prefix);

    void delKey(String key);

}
