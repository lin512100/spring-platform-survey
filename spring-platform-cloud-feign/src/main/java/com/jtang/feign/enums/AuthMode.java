package com.jtang.feign.enums;

import lombok.Getter;

/**
 * SpringSecurity授权模式
 * @date 2020/7/4 15:38
 * @author LinJinTang
 */
@Getter
public enum AuthMode {
    PASSWORD("password","密码模式"),
    CLIENT_CREDENTIALS("client_credentials","客户端模式");

    private String mode;

    private String message;

    AuthMode(String mode, String message){
        this.mode = mode;
        this.message = message;

    }

}
