package com.jtang.task.service;

import com.jtang.task.entity.TbTask;

import java.util.List;

public interface ScheduleService {

    void initSchedule(List<TbTask> tbTasks);
}
