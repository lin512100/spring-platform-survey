package com.jtang.zuul.service;


import javax.servlet.http.HttpServletRequest;

/**
 * @date 2020/7/4 23:17
 * @author LinJinTang
 */
public interface AuthService {

    /**
     * JWT请求头
     * @param request ServerHttpRequest
     * @return String
     * */
    String getJwtFromHeader(HttpServletRequest request);

    /**
     * 查询身份令牌
     * @param request ServerHttpRequest
     * @return String
     * */
    String getTokenFromCookie(HttpServletRequest request);

    /**
     * 查询令牌的有效期
     * @param accessToken Token
     * @return id
     * */
    boolean isExpire(String accessToken);

}
