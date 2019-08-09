package com.scorer.client.aop;

import com.scorer.client._Excptions.TokenTimeOutException;
import com.scorer.client.tools.SysContent;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;


@Component
@Aspect
@Order(2)  //定义切面执行的优先级，数字越低，优先级越高
public class CheckToken {

    @Resource
    private RedisTemplate<String, String> redisTemplate;


    @Pointcut("execution(* com.scorer.client.controller.AccountController.account*(..))")
    public void CheckAccountTokenController() {
    }


    @Around("CheckAccountTokenController()")
    public Object appValToken(ProceedingJoinPoint thisJoinPoint) throws Throwable {
        System.err.println("Check token app start!");
        HttpServletRequest request = SysContent.getRequest();
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        String token = request.getHeader("token");
        String uid = request.getHeader("uid");
        String uidToken = operations.get("uid_token:" + uid);
        if (uidToken == null || !uidToken.equals(token)) {
            throw new TokenTimeOutException("token fail");
        } else {
            System.err.println("Check token app success!");
            operations.set("uid_token_wx_app:" + uid, token, 15, TimeUnit.DAYS);
            System.err.println("Check token app proceed over!");
            return thisJoinPoint.proceed();
        }
    }

}
