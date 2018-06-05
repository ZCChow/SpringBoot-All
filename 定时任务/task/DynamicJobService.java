package com.example.ela.task;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DynamicJobService {
    @Autowired
    private JobNameMapper repository;
    //通过Id获取Job
    public JobName getJobEntityById(Integer id) {
        return repository.selectByPrimaryKey(id);
    }

    //从数据库中加载获取到所有Job
     public List<JobName> loadJobs() {
        List<JobName> list = new ArrayList<>();
        return list;
    }

     //获取JobDataMap.(Job参数对象)
     public JobDataMap getJobDataMap(JobName job) {
               JobDataMap map = new JobDataMap();
               map.put("name", job.getName());
               map.put("group", job.getGroup());
               map.put("cronExpression", job.getCron());
               map.put("parameter", job.getParameter());
               map.put("JobDescription", job.getDescription());
               map.put("vmParam", job.getVmParam());
               map.put("jarPath", job.getJarPath());
               map.put("status", job.getStatus());
               return map;
           }
    //获取JobDetail,JobDetail是任务的定义,而Job是任务的执行逻辑,JobDetail里会引用一个Job Class来定义
            public JobDetail geJobDetail(JobKey jobKey, String description, JobDataMap map) {
                return JobBuilder.newJob(DynamicJob.class)
                        .withIdentity(jobKey)
                        .withDescription(description)
                        .setJobData(map)
                        .storeDurably()
                        .build();
            }
     //获取Trigger (Job的触发器,执行规则)
             public Trigger getTrigger(JobName job) {
                 return TriggerBuilder.newTrigger()
                         .withIdentity(job.getName(), job.getGroup())
                         .withSchedule(CronScheduleBuilder.cronSchedule(job.getCron()))
                         .build();
             }
    //获取JobKey,包含Name和Group
            public JobKey getJobKey(JobName job) {
                return JobKey.jobKey(job.getName(), job.getGroup());
            }
}
