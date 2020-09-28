package com.jtang.base.enums;

import lombok.Getter;

@Getter
public enum JobStatusEnum {

    /**
     * 0=停止
     */
    STOP("0", "停止"),
    /**
     * 1=运行
     */
    RUNNING("1", "运行");

    private String value = null;
    private String code = null;

    JobStatusEnum(String code, String value) {
        this.value = code;
        this.code = value;
    }
}
