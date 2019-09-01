package com.scorer.clientPhone._DBController;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface RedisLock_PARAMETER_C {

    /**
     * 加长redis的锁key的名字,做特定锁
     */
}