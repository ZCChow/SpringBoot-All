package com.xianjinxia.bigdata.personas.log;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;

public class KafkaUtils{
    private final KafkaProducer<String, String> producer;
    private final String topic;
    private final boolean isAsync;


    public KafkaUtils(String topic, boolean isAsync,String brokerIp) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerIp);//broker 集群地址
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, "MsgProducer");//自定义客户端id
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");//key 序列号方式
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");//value 序列号方式
//        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,CustomPartitioner.class.getCanonicalName());//自定义分区函数

//        properties.load("properties配置文件");
        this.producer = new KafkaProducer<String, String>(properties);
        this.topic = topic;
        this.isAsync = isAsync;
    }

      void  sendMsg(String msg){
          if (isAsync) {//异步
              producer.send(new ProducerRecord<String, String>(this.topic,msg));
          }
        }


}
