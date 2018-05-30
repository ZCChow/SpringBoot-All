package com.xianjinxia.bigdata.personas.log;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
public class LogInfo {
    String eventType;
    String appName;
    String createTime;
    String logLevel;
    String ip;
    String msg;
}
