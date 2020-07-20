package com.jtang.oauth.service.impl;

import com.alibaba.fastjson.JSON;
import com.jtang.base.exception.ExceptionCast;
import com.jtang.common.model.account.AuthToken;
import com.jtang.common.model.account.request.LoginRequest;
import com.jtang.common.model.account.response.AuthCode;
import com.jtang.feign.properties.AuthProperties;
import com.jtang.feign.service.FeignService;
import com.jtang.oauth.service.AuthService;
import com.jtang.oauth.utils.AuthCookieUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @version 1.0
 **/
@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthProperties authProperties;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private FeignService feignService;

    @Override
    public String getToken(LoginRequest loginRequest) {
        if(loginRequest == null || StringUtils.isEmpty(loginRequest.getUsername())){
            ExceptionCast.cast(AuthCode.AUTH_USERNAME_NONE);
        }

        if(StringUtils.isEmpty(loginRequest.getPassword())){
            ExceptionCast.cast(AuthCode.AUTH_PASSWORD_NONE);
        }
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        //申请令牌
        AuthToken authToken =  feignService.getTokenByPassword(username, password);

        //用户身份令牌
        String accessToken = authToken.getAccess_token();

        //将令牌存储到redis
        boolean result = this.saveToken(accessToken, JSON.toJSONString(authToken), authProperties.getTokenValiditySeconds());
        if (!result) {
            ExceptionCast.cast(AuthCode.AUTH_LOGIN_TOKEN_SAVE_FAIL);
        }
        //将令牌存储到cookie
        AuthCookieUtils.saveCookie(accessToken,authProperties);
        return accessToken;
    }

    @Override
    public void logout() {
        // 取出cookie中的用户身份令牌
        String uid = AuthCookieUtils.getTokenFormCookie();
        //删除redis中的token
        AuthCookieUtils.delToken(redissonClient,uid);
        //清除cookie
        AuthCookieUtils.clearCookie(uid,authProperties.getCookieDomain());
    }

    @Override
    public String jwtToken() {
        //取出cookie中的用户身份令牌
        String uid = AuthCookieUtils.getTokenFormCookie();
        if(uid == null){ return null; }
        //拿身份令牌从redis中查询jwt令牌
        AuthToken userToken = AuthCookieUtils.getUserToken(redissonClient, uid);
        if(userToken != null){
            //将jwt令牌返回给用户
            return userToken.getJwt_token();
        }
        return null;
    }

    /**
     * @param accessToken 用户身份令牌
     * @param content  内容就是AuthToken对象的内容
     * @param ttl 过期时间
     * @return boolean
     */
    private boolean saveToken(String accessToken,String content,long ttl){

        String key = "user_token:" + accessToken;
        redissonClient.getBucket(key).set(content, ttl, TimeUnit.SECONDS);
        return true;
    }

}
