package com.jtang.oauth.service;

import com.jtang.common.model.oauth.request.LoginRequest;

/**
 * @date 2020/7/2 21:05
 * @author LinJinTang
 */
public interface AuthService {
    /**
     * 用户登录
     * @param loginRequest {@link LoginRequest}
     * @return {@link String}
     * */
    String getToken(LoginRequest loginRequest);

    /**
     * 用户退出
     * */
    void logout();

    /**
     * 获取JWT令牌
     * @return {@link String}
     * */
    String jwtToken();
}
