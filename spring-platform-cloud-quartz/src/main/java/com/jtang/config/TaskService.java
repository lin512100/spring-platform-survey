package com.jtang.config;


import com.jtang.task.entity.TbTask;
import org.quartz.SchedulerException;

public interface TaskService {

    TbTask get(Long id);

    int save(TaskDO taskScheduleJob);

    int update(TbTask taskScheduleJob);

    int remove(Long id);

    int removeBatch(Long[] ids);

    void initSchedule() throws SchedulerException;

    void changeStatus(Long jobId, String jobStatus) throws SchedulerException;

    void updateCron(Long jobId) throws SchedulerException;

    void run(TaskDO scheduleJob) throws SchedulerException;
}