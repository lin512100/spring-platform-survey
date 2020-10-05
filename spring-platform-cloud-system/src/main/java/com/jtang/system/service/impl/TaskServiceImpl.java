package com.jtang.system.service.impl;

import com.jtang.config.QuartzManager;
import com.jtang.system.entity.Task;
import com.jtang.system.mapper.TaskMapper;
import com.jtang.system.service.ScheduleService;
import com.jtang.system.service.TaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* 定时任务 服务实现类
* @author jtang
* @since 2020-09-28
*/
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {

    @Autowired
    private QuartzManager quartzManager;

    @Autowired
    private ScheduleService scheduleService;

    @Override
    public void initSchedule() throws SchedulerException {
        // 这里获取任务信息数据
        List<Task> jobList = list();
        if(jobList != null && jobList.size() > 0){
            scheduleService.initSchedule(jobList);
        }
    }

    @Override
    public int remove(Long id) {
        return 0;
    }

    @Override
    public int removeBatch(Long[] ids) {
        return 0;
    }

    @Override
    public void changeStatus(Long jobId, String jobStatus) throws SchedulerException {

    }

    @Override
    public void updateCron(Long jobId) throws SchedulerException {

    }

    @Override
    public void run(Task scheduleJob) throws SchedulerException {
        quartzManager.runJobNow(scheduleJob);
    }
}

