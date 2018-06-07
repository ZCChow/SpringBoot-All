package com.example.demo;


import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import javax.jms.Message;

@Service
public class Producer {
    @Autowired
  // private JmsMessagingTemplate  jmsMessagingTemplate;
    private JmsTemplate jmsTemplate;
    public void sendMsg(String destination,String message){
        System.out.println("发送-------" +message);
        Destination destination1=new ActiveMQQueue(destination);
      //  jmsMessagingTemplate.convertAndSend(destination1,message);
        jmsTemplate.convertAndSend(destination,message);
    }

    public void pubMsg(String destination,String message) {
        System.out.println("发送topic-------" +message);
        Destination destination1=new ActiveMQTopic(destination);
       // jmsMessagingTemplate.convertAndSend(destination1,message);
        jmsTemplate.convertAndSend(destination,message);
    }
}
