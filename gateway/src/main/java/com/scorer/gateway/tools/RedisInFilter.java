package com.scorer.gateway.tools;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class RedisInFilter {

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    private static RedisTemplate<String,String> redisTemplate_temp;

    @PostConstruct
    public void initSelf() {
        redisTemplate_temp = this.redisTemplate;
    }

    public static boolean CheckNonceStr(String nonceStr) {
        ValueOperations<String, String> operations = redisTemplate_temp.opsForValue();
        if(operations.setIfAbsent("NonceStr_SAVE:" + nonceStr, nonceStr)){
            redisTemplate_temp.expire("NonceStr_SAVE:" + nonceStr,10,TimeUnit.MINUTES);
            return true;
        }else{
            return false;
        }
    }

}
