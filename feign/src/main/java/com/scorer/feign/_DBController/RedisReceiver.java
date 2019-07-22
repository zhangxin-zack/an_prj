package com.scorer.feign._DBController;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@SuppressWarnings("unused")
public class RedisReceiver {

    @Value("${spring.redis.database}")
    private String redis_database;

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    public void expiredMessage(String expired_key) {
        ValueOperations<String,Object> valueOperations = redisTemplate.opsForValue();
        System.err.println("expired_key_in_"+redis_database+"-->"+expired_key);
        //过期KEY执行方法
    }

}