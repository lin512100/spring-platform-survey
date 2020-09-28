package com.jtang.task.service.impl;

import com.jtang.base.enums.JobStatusEnum;
import com.jtang.config.QuartzManager;
import com.jtang.task.entity.TbTask;
import com.jtang.task.mapper.TbTaskMapper;
import com.jtang.task.service.ITbTaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
* 定时任务 服务实现类
* @author jtang
* @since 2020-09-28
*/
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class TbTaskServiceImpl extends ServiceImpl<TbTaskMapper, TbTask> implements ITbTaskService {

    @Autowired
    private QuartzManager quartzManager;

    @Override
    public void initSchedule() throws SchedulerException {
        // 这里获取任务信息数据
        List<TbTask> jobList = list();
        log.info("正在获取队列任务......");
        for (TbTask task : jobList) {
            if (JobStatusEnum.RUNNING.getCode().equals(task.getJobStatus())) {
                quartzManager.addJob(task);
                quartzManager.runJobNow(task);
            }
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
    public void run(TbTask scheduleJob) throws SchedulerException {
        quartzManager.runJobNow(scheduleJob);
    }
}

