package com.jtang.common.model.account.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 允许访问的操作
 * @author linjt
 * @date 2020/7/20
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HandleAllow implements Serializable {

    /** 模块名称 */
    private String server;

    /** 访问路径 */
    private String url;

    /** 访问方法 */
    private String method;
}
