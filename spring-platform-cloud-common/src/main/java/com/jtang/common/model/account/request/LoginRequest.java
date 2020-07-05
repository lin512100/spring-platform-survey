package com.jtang.common.model.account.request;

import lombok.Getter;
import lombok.Setter;

/**
 * 登录请求
 * @date 2020/7/2 20:39
 * @author LinJinTang
 */
@Getter
@Setter
public class LoginRequest {

    /** 用户名 */
    String username;
    /** 密码 */
    String password;
    /** 验证码 */
    String verifycode;
}
