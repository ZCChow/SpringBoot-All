package com.xianjinxia.bigdata.personas.log;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import com.alibaba.fastjson.JSONObject;



import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CompletableFuture;

public class KafkaLogAppender extends AppenderBase<ILoggingEvent>{
    private  final  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static PropertiesLoader propertiesLoader= new PropertiesLoader("application.properties");;
    static String hostName;
    static String appName;
    static KafkaUtils kafkaUtils;
    LogInfo logInfo;

    static {
        try {
            hostName = InetAddress.getLocalHost().getHostAddress();
            appName=propertiesLoader.getProperty("appName");
            kafkaUtils=new KafkaUtils(propertiesLoader.getProperty("kafkaTopic"),true,propertiesLoader.getProperty("brokerIp"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void append(ILoggingEvent eventObject)
    {

            System.out.println(eventObject);
      /*  logInfo=new LogInfo("normal",appName,formatter.format(eventObject.getTimeStamp()),eventObject.getLevel().levelStr,hostName,eventObject.getMessage(),null);
        CompletableFuture<String> completableFuture=CompletableFuture.supplyAsync(()->{
            //模拟执行耗时任务
            kafkaUtils.sendMsg(JSONObject.toJSON(logInfo).toString());
            //返回结果
            return "result";
        });*/
    }


}
