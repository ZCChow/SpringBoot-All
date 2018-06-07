package com.example.demo;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class SubConsumer {

    @JmsListener(destination = "topic1",containerFactory = "topicContainerFactory1")
    public void subReceive(String text) {
        System.out.println("收到topic信息1------"+text);
    }


    @JmsListener(destination = "topic1",containerFactory = "topicContainerFactory2")
    public void subReceive2(String text) {
        System.out.println("收到topic信息2------"+text);
    }
}
