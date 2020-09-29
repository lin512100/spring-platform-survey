package com.jtang.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum JobStatusEnum {

    /**
     * 0=停止
     */
    STOP(0, "停止"),
    /**
     * 1=运行
     */
    RUNNING(1, "运行");

    private final int status ;
    private final String msg;
}
