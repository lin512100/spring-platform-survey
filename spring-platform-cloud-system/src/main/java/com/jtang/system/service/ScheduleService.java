package com.jtang.system.service;

import com.jtang.system.entity.Task;

import java.util.List;

public interface ScheduleService {

    void initSchedule(List<Task> tasks);
}
