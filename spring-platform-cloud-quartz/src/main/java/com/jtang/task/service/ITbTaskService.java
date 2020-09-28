package com.jtang.task.service;

import com.jtang.task.entity.TbTask;
import com.baomidou.mybatisplus.extension.service.IService;
import org.quartz.SchedulerException;

import java.util.List;
import java.util.Map;

/**
* 定时任务 服务类
* @author jtang
* @since 2020-09-28
*/
public interface ITbTaskService extends IService<TbTask> {

    /**
     * 周期任务初始化
     * @throws SchedulerException 异常
     * */
    void initSchedule() throws SchedulerException;

    int remove(Long id);

    int removeBatch(Long[] ids);

    void changeStatus(Long jobId, String jobStatus) throws SchedulerException;

    void updateCron(Long jobId) throws SchedulerException;

    void run(TbTask scheduleJob) throws SchedulerException;

}
