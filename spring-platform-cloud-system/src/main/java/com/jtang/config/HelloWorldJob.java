package com.jtang.config;

import java.time.LocalDateTime;
import java.util.Date;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;


//作业不并发
@Component
@DisallowConcurrentExecution
public class HelloWorldJob implements Job{

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {

        System.out.println("欢迎使用platform, 这是一个定时任务  --lin512100!"+ LocalDateTime.now());

    }

}