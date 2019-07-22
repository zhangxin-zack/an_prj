package com.scorer.client._Steam.test;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface TestTopicB_Out {

    //绑定名要一致(@Input-->@StreamListener)在没有指定@Input的名字的时候自动设置方法名为名字--输入通道
    String TOPIC = "TestTopicB";

    @Output(TOPIC)
    MessageChannel output();

}
