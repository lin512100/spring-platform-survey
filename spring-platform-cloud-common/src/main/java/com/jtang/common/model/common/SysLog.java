package com.jtang.common.model.common;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SysLog {

    /** 请求IP */
    private String ip;

    /** 請求地址 */
    private String url;

    /** 請求方式 */
    private String method;

    /** 功能描述 */
    private String function;

    /** 请求模快 */
    private String module;

    /** 查询参数 */
    private String params;

    /** 微服务模快 */
    private String service;

    /** 执行结果 */
    private int success;

    /** 失败原因 */
    private String error;

    /** 操作开始时间 */
    private LocalDateTime startDate;

    /** 操作结束时间 */
    private LocalDateTime endDate;

}
