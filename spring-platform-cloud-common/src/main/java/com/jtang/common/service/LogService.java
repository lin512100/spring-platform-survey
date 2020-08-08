package com.jtang.common.service;

import com.jtang.common.model.common.SysLog;

public interface LogService {

    /**
     * 日志新增
     * */
    void insert(SysLog sysLog);
}
