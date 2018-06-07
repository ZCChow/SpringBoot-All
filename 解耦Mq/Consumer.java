package com.example.demo;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    @JmsListener(destination = "*")
    public void receiveMsg(String text){

        System.out.println("收到消息-------"+text);
    }


}
