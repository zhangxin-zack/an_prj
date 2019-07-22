package com.scorer.client._Steam.test;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding({TestTopicB_In.class})
public class TestStream {

    @StreamListener(TestTopicB_In.TOPIC)
    public void receiveMSG(Person person) {
        System.out.println("Received_B : " + person);
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