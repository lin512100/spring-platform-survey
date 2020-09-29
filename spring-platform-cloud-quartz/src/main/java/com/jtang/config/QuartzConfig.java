package com.jtang.config;

import com.jtang.task.service.ScheduleService;
import com.jtang.task.service.impl.ScheduleServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 定时任务默认初始化方法
 * @author lin512100
 * @date 2020/9/29
 */
@Configuration
@EnableScheduling
public class QuartzConfig {

    @Bean
    @ConditionalOnMissingBean
    public ScheduleService scheduleService(){
        return new ScheduleServiceImpl();
    }


}
