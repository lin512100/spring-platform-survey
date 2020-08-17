package com.jtang.zuul.service.impl;

import com.jtang.redisson.constants.RedisConstants;
import com.jtang.redisson.core.RedisHolder;
import com.jtang.zuul.service.AuthService;
import com.jtang.zuul.utils.CookieUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @date 2020/7/4 23:17
 * @author LinJinTang
 */
@Service
public class AuthServiceImpl implements AuthService {

    /** Authorization 前缀 */
    private final static String AUTH_PRE = "Bearer ";

    @Autowired
    private RedisHolder redisHolder;


    @Override
    public String getJwtFromHeader(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        // 不带验证信息
        if (authorization == null) {
            return null;
        }

        // 判断是否是以 Bearer 开头的
        if (!authorization.startsWith(AUTH_PRE)) {
            return null;
        }

        //取到jwt令牌
        return authorization.substring(7);
    }

    @Override
    public String getTokenFromCookie(HttpServletRequest request) {
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
        return redisHolder.getInstance(RedisConstants.REDIS_DATABASE_ZUUL).getBucket(key).get() == null;
    }
}
