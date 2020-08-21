package com.jtang.common.service;

import com.jtang.common.model.common.SysLog;
import org.springframework.scheduling.annotation.Async;

public interface LogService {

    /**
     * 日志新增
     * */
    @Async("logExecutor")
    void insert(SysLog sysLog);
}
