package com.scorer.clientPhone.tools;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class TokenTools {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    private static RedisTemplate<String, Object> redisTemplate_temp;

    @PostConstruct
    public void initSelf() {
        redisTemplate_temp = this.redisTemplate;
    }

    public static String generateTokenAPP(Long accountId) {
        ValueOperations<String, Object> operations = redisTemplate_temp.opsForValue();
        Map<String, Object> map = new HashMap<>();
        map.put("accountId", accountId);                                //根据用户ID生成token
        map.put("LoginTime", System.currentTimeMillis());               //根据用户登录时间生成token
        String token = com.scorer.clientPhone.tools.Jwt.createToken(map);
        operations.set("uid_app_token:" + accountId, token, 15, TimeUnit.DAYS);
        return token;
    }

    public static String generateTokenSchool(Long manageId) {
        ValueOperations<String, Object> operations = redisTemplate_temp.opsForValue();
        Map<String, Object> map = new HashMap<>();
        map.put("manageId", manageId);                                       //根据用户ID生成token
        map.put("LoginTime", System.currentTimeMillis());                    //根据用户登录时间生成token
        String token = Jwt.createToken(map);
        operations.set("uid_school_token:" + manageId, token, 15, TimeUnit.DAYS);
        return token;
    }

    public static void DelAdminTokenByUid(Integer uid) {
        ValueOperations<String, Object> operations = redisTemplate_temp.opsForValue();
        String session_id = (String) operations.get("admin_uid_season_id:" + uid);
        redisTemplate_temp.delete("admin_uid_season_id:" + uid);
        if (!ObjectUtils.isEmpty(session_id)) {
            redisTemplate_temp.delete("admin_token_web:" + session_id);
            redisTemplate_temp.delete("admin_uid_web:" + session_id);
        }
    }

    public static void DelUserTokenByUid(Integer uid) {
        redisTemplate_temp.delete("uid_token_wx_app:" + uid);
    }

    public static void DelAdminTokenBySessionId(String session_id) {
        redisTemplate_temp.delete("admin_token_web:" + session_id);
        redisTemplate_temp.delete("admin_uid_web:" + session_id);
    }


    public static void DELToken_KF_Web(Integer aid) {
        redisTemplate_temp.delete("kf_token_web:" + aid);
    }
}
