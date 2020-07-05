package com.jtang.gateway.service.impl;

import com.jtang.gateway.service.AuthService;
import com.jtang.gateway.utils.CookieUtil;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @date 2020/7/4 23:17
 * @author LinJinTang
 */
@Service
public class AuthServiceImpl implements AuthService {
    /** 默认redis数据库 */
    private static final int DEFAULT_REDIS_DATABASE = 0;

    @Autowired
    private RedissonClient redissonClient;


    /** Authorization 前缀 */
    private final static String AUTH_PRE = "Bearer ";


    @Override
    public String getJwtFromHeader(ServerHttpRequest request) {
        List<String> authorizations = request.getHeaders().get("Authorization");
        // 不带验证信息
        if(authorizations == null || authorizations.size() == 0){
            return null;
        }
        String authorization = authorizations.get(0);
        // 判断是否是以 Bearer 开头的
        if(!authorization.startsWith(AUTH_PRE)){
            return null;
        }
        //取到jwt令牌
        return authorization.substring(7);
    }

    @Override
    public String getTokenFromCookie(ServerHttpRequest request) {
        Map<String, String> cookieMap = CookieUtil.readCookie(request, "uid");
        String accessToken = cookieMap.get("uid");
        if(StringUtils.isEmpty(accessToken)){
            return null;
        }
        return accessToken;
    }

    @Override
    public boolean isExpire(String accessToken) {
        String key = "user_token:"+accessToken;
        return redissonClient.getBucket(key).get() == null;
    }
}
