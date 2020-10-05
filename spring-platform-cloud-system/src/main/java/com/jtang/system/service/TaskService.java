package com.jtang.system.service;

import com.jtang.system.entity.Task;
import com.baomidou.mybatisplus.extension.service.IService;
import org.quartz.SchedulerException;

/**
* 定时任务 服务类
* @author jtang
* @since 2020-09-28
*/
public interface TaskService extends IService<Task> {

    /**
     * 周期任务初始化
     * @throws SchedulerException 异常
     * */
    void initSchedule() throws SchedulerException;

    int remove(Long id);

    int removeBatch(Long[] ids);

    void changeStatus(Long jobId, String jobStatus) throws SchedulerException;

    void updateCron(Long jobId) throws SchedulerException;

    void run(Task scheduleJob) throws SchedulerException;

}
