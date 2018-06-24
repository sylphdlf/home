package com.dlf.business.manager.redis;

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

    /**
     * StringRedisTemplate
     * @param key
     * @return
     */
    String get(String key);
}
