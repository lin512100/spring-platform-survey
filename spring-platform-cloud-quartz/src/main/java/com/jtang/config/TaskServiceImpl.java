package com.jtang.config;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jtang.task.entity.TbTask;
import com.jtang.task.service.ITbTaskService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private ITbTaskService taskService;

    @Autowired
    QuartzManager quartzManager;

    @Override
    public TbTask get(Long id) {
        return taskService.getBaseMapper().selectById(id);
    }

    @Override
    public int save(TaskDO taskScheduleJob) {
        return 0;
    }

    @Override
    public int update(TbTask taskScheduleJob) {
        return 0;
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
    public void initSchedule() throws SchedulerException {
        // 这里获取任务信息数据
        List<TbTask> jobList = taskService.getBaseMapper().selectList(new QueryWrapper<TbTask>());
        for (TbTask task : jobList) {
            if (JobStatusEnum.RUNNING.getCode().equals(task.getJobStatus())) {
                quartzManager.addJob(task);
            }

        }
    }

    @Override
    public void changeStatus(Long jobId, String jobStatus) throws SchedulerException {
        TbTask task = get(jobId);
        if (task == null) {
            return;
        }
        if (JobStatusEnum.STOP.getCode().equals(jobStatus)) {
            quartzManager.deleteJob(task);
            task.setJobStatus(JobStatusEnum.STOP.getCode());
        } else {
            if (!JobStatusEnum.RUNNING.getCode().equals(jobStatus)) {
            } else {
                task.setJobStatus(JobStatusEnum.RUNNING.getCode());
                quartzManager.addJob(task);
            }
        }
        update(task);
    }

    @Override
    public void updateCron(Long jobId) throws SchedulerException {
        TbTask task = get(jobId);
        if (task == null) {
            return;
        }
        if (JobStatusEnum.RUNNING.getCode().equals(task.getJobStatus())) {
            quartzManager.updateJobCron(task);
        }
        update(task);
    }

    @Override
    public void run(TaskDO task) throws SchedulerException {
        quartzManager.runJobNow(task);
    }

}