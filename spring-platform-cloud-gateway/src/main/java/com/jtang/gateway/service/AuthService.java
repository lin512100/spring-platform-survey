package com.jtang.gateway.service;

import org.springframework.http.server.reactive.ServerHttpRequest;

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
    String getJwtFromHeader(ServerHttpRequest request);

    /**
     * 查询身份令牌
     * @param request ServerHttpRequest
     * @return String
     * */
    String getTokenFromCookie(ServerHttpRequest request);

    /**
     * 查询令牌的有效期
     * @param accessToken Token
     * @return id
     * */
    boolean isExpire(String accessToken);

}
