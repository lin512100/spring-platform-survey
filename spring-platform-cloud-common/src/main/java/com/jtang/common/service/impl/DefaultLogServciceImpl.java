package com.jtang.common.service.impl;

import com.jtang.common.model.common.SysLog;
import com.jtang.common.service.LogService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultLogServciceImpl implements LogService {

    @Override
    public void insert(SysLog sysLog) {
      log.info(sysLog.toString());
    }
}
