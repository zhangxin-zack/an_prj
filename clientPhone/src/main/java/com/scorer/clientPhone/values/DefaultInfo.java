package com.scorer.clientPhone.values;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class DefaultInfo {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    private Integer maxHeart = 150;
    private Integer maxTemp = 38;
    private Double schoolR = 200D;

    public int maxHeart() {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        Integer maxHeartSaved = (Integer) operations.get("MaxHeart");
        return maxHeartSaved != null && maxHeartSaved > 0 ? maxHeartSaved : maxHeart;
    }

    public int maxTemp() {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        Integer maxTempSaved = (Integer) operations.get("MaxTemp");
        return maxTempSaved != null && maxTempSaved > 0 ? maxTempSaved : maxTemp;
    }

    public double schoolR() {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        Double schoolRSaved = (Double) operations.get("SchoolR");
        return schoolRSaved != null && schoolRSaved > 0 ? schoolRSaved : schoolR;
    }

    public void setMaxHeart(Integer maxHeart) {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        operations.set("MaxHeart", maxHeart);
    }

    public void setMaxTemp(Integer maxTemp) {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        operations.set("MaxTemp", maxTemp);
    }

    public void setSchoolR(Double schoolR) {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        operations.set("SchoolR", schoolR);
    }
}
