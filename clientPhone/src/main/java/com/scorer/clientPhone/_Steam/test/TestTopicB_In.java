package com.scorer.clientPhone._Steam.test;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface TestTopicB_In {
    //绑定名要一致(@Input-->@StreamListener)在没有指定@Input的名字的时候自动设置方法名为名字--输入通道
    String TOPIC = "TestTopicB";

    @Input(TOPIC)
    SubscribableChannel input();

}