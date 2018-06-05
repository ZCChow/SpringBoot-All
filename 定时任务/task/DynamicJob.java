package com.example.ela.task;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

@Slf4j
@DisallowConcurrentExecution //不允许并发
public class DynamicJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        JobDataMap map = jobExecutionContext.getMergedJobDataMap();
        log.info("-------------------"+map);
    }
}
