package com.jtang.system.service.impl;

import com.jtang.base.enums.JobStatusEnum;
import com.jtang.config.QuartzManager;
import com.jtang.system.entity.Task;
import com.jtang.system.service.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Slf4j
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private QuartzManager quartzManager;

    @Override
    public void initSchedule(List<Task> tasks) {
        log.info("定时任务默认初始化~~~~~");
        for (Task task : tasks) {
            if (JobStatusEnum.RUNNING.getStatus() == task.getJobStatus()) {
                quartzManager.addJob(task);
            }
        }
    }
}
