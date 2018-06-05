package com.example.ela.task;

import lombok.Data;

@Data
public class JobName {
    private Integer id;
    private String name;          //job名称
    private String group;         //job组名
    private String cron;          //执行的cron
    private String parameter;     //job的参数
    private String description;   //job描述信息
    private String vmParam;       //vm参数
    private String jarPath;       //job的jar路径,在这里我选择的是定时执行一些可执行的jar包
    private String status;
}
