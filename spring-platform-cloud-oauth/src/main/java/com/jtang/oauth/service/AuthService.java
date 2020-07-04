package com.jtang.oauth.service;

import com.jtang.common.model.oauth.AuthToken;
import com.jtang.common.model.oauth.request.LoginRequest;

/**
 * @date 2020/7/2 21:05
 * @author LinJinTang
 */
public interface AuthService {

    /**
     * 用户认证申请令牌，将令牌存储到redis
     * @param username 用户名
     * @param password 密码
     * @param clientId 客户端ID
     * @param clientSecret 客户端密码
     * @return {@link AuthToken}
     * */
    AuthToken getToken(String username, String password, String clientId, String clientSecret);

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
