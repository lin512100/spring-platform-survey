package com.jtang.feign.service;

import com.jtang.common.model.oauth.AuthToken;
import com.jtang.feign.enums.AuthMode;

/**
 * @date 2020/7/4 15:29
 * @author LinJinTang
 */
public interface FeignService {

    /**
     * 密码模式获取Token
     * @param username 用户名
     * @param password 密码
     * @return {@link AuthToken}
     * */
    AuthToken getTokenByPassword(String username, String password);

    /**
     * 密码模式获取Token
     * @return {@link String}
     * */
    String getTokenByClientCredentials();
}
