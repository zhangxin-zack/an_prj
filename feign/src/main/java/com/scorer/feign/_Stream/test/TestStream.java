package com.scorer.feign._Stream.test;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;


@EnableBinding({TestTopicA_In.class, TestTopicB_Out.class})
public class TestStream {

    @Resource
    private TestTopicB_Out testTopicB;

    @StreamListener(TestTopicA_In.TOPIC)
    public void receiveMSG(Person person) {
        System.out.println("Received_A : " + person);
        MessageBuilder<Person> messageBuilder = MessageBuilder.withPayload(person);
        Message<Person> build = messageBuilder.build();
        testTopicB.output().send(build);
    }

    public static class Person {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String toString() {
            return this.name;
        }
    }

}