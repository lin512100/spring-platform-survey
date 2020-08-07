package com.jtang.common.model.common;

import lombok.*;

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

    /** 请求模快 */
    private String module;

    /** 微服务模快 */
    private String service;


}
