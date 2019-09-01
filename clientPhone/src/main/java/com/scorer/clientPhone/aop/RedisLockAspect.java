package com.scorer.clientPhone.aop;

import com.scorer.clientPhone._DBController.RedisLock_METHOD;
import com.scorer.clientPhone._DBController.RedisLock_PARAMETER_A;
import com.scorer.clientPhone._DBController.RedisLock_PARAMETER_B;
import com.scorer.clientPhone._DBController.RedisLock_PARAMETER_C;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@Component
@Aspect
@Order(5)  //定义切面执行的优先级，数字越低，优先级越高
public class RedisLockAspect {

    @Resource
    private RedisTemplate<String, Long> redisTemplate;

    @Pointcut("execution(* com.scorer.clientPhone..*(..)) && @annotation(com.scorer.clientPhone._DBController.RedisLock_METHOD)")
    private void lockPoint() {
    }

    @Around("lockPoint()")
    public Object checkRedisLock(ProceedingJoinPoint thisJoinPoint) throws Throwable {
        System.err.println("in checkRedisLock");
        Signature signature = thisJoinPoint.getSignature();//方法签名
        Method method = ((MethodSignature) signature).getMethod();
        Method realMethod = thisJoinPoint.getTarget().getClass().getDeclaredMethod(signature.getName(), method.getParameterTypes());
        RedisLock_METHOD lockInfo = realMethod.getAnnotation(RedisLock_METHOD.class);
        StringBuilder lockKeyValueT = new StringBuilder(lockInfo.value());
        Annotation[][] paramAnnotationArrays = realMethod.getParameterAnnotations();
        Object[] args = thisJoinPoint.getArgs();
        for (int ix = 0; ix < paramAnnotationArrays.length; ix++) {
            RedisLock_PARAMETER_A redisLock_parameter = getAnnotation(RedisLock_PARAMETER_A.class, paramAnnotationArrays[ix]);
            if (redisLock_parameter != null) {
                Object arg = args[ix];
                if (arg != null) {
                    lockKeyValueT.append(arg.toString());   //加上自定义字段
                    break;
                }
            }
        }
        for (int ix = 0; ix < paramAnnotationArrays.length; ix++) {
            RedisLock_PARAMETER_B redisLock_parameter = getAnnotation(RedisLock_PARAMETER_B.class, paramAnnotationArrays[ix]);
            if (redisLock_parameter != null) {
                Object arg = args[ix];
                if (arg != null) {
                    lockKeyValueT.append(arg.toString());   //加上自定义字段
                    break;
                }
            }
        }
        for (int ix = 0; ix < paramAnnotationArrays.length; ix++) {
            RedisLock_PARAMETER_C redisLock_parameter = getAnnotation(RedisLock_PARAMETER_C.class, paramAnnotationArrays[ix]);
            if (redisLock_parameter != null) {
                Object arg = args[ix];
                if (arg != null) {
                    lockKeyValueT.append(arg.toString());   //加上自定义字段
                    break;
                }
            }
        }
        String lockKeyValue = lockKeyValueT.toString();
        System.err.println("lockKeyValue ----> " + lockKeyValue);
        boolean lock = false;
        Object nextAOP = null;
        while (!lock) {
            long timestamp = System.currentTimeMillis() + lockInfo.keepMills();
            lock = setNX(lockKeyValue, timestamp);  //尝试设置锁
            long now = System.currentTimeMillis();
            if (lock || now > getLock(lockKeyValue)) {
                setNewLock(lockKeyValue, timestamp, lockInfo.keepMills());
//                System.err.println("得到锁...");
                lock = true;    //得到锁,执行方法,跳出while
                nextAOP = thisJoinPoint.proceed();
                if (lockInfo.action().equals(RedisLock_METHOD.LockFailAction.CONTINUE)) {  //释放锁
                    releaseLock(lockKeyValue);
//                    System.err.println("释放锁");
                }
            } else {
                if (lockInfo.action().equals(RedisLock_METHOD.LockFailAction.CONTINUE)) {
//                    System.err.println("稍后重新请求锁...");
                    Thread.sleep(lockInfo.sleepMills());
                } else {
//                    System.err.println("放弃锁...");
                    break;
                }
            }
        }
        return nextAOP;
    }

    private <T extends Annotation> T getAnnotation(final Class<T> annotationClass, final Annotation[] annotations) {
        if (annotations != null && annotations.length > 0) {
            for (final Annotation annotation : annotations) {
                if (annotationClass.equals(annotation.annotationType())) {
                    return (T) annotation;
                }
            }
        }

        return null;
    }

    private boolean setNX(String key, Long value) {
        ValueOperations<String, Long> valueOperations = redisTemplate.opsForValue();
        return valueOperations.setIfAbsent(key, value);
    }

    private long getLock(String key) {
        ValueOperations<String, Long> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    private void setNewLock(String key, Long value, Long timeout) {
        ValueOperations<String, Long> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value, timeout, TimeUnit.MILLISECONDS);
    }

    private void releaseLock(String key) {
        redisTemplate.delete(key);
    }
}