package com.jtang.oauth.service.impl;

import com.alibaba.fastjson.JSON;
import com.jtang.common.client.ServiceConstants;
import com.jtang.common.exception.ExceptionCast;
import com.jtang.common.model.oauth.AuthToken;
import com.jtang.common.model.oauth.request.LoginRequest;
import com.jtang.common.model.oauth.response.AuthCode;
import com.jtang.common.utils.CookieUtil;
import com.jtang.oauth.properties.AuthProperties;
import com.jtang.oauth.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Map;
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
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String login(LoginRequest loginRequest) {
        if(loginRequest == null || StringUtils.isEmpty(loginRequest.getUsername())){
            ExceptionCast.cast(AuthCode.AUTH_USERNAME_NONE);
        }

        if(StringUtils.isEmpty(loginRequest.getPassword())){
            ExceptionCast.cast(AuthCode.AUTH_PASSWORD_NONE);
        }
        //账号
        String username = loginRequest.getUsername();

        //密码
        String password = loginRequest.getPassword();

        //申请令牌
        AuthToken authToken =  login(username, password, authProperties.getClientId(), authProperties.getClientSecret());

        //用户身份令牌
        String accessToken = authToken.getAccess_token();
        //将令牌存储到cookie
        this.saveCookie(accessToken);
        return accessToken;
    }


    @Override
    public AuthToken login(String username, String password, String clientId, String clientSecret) {

        //请求spring security申请令牌
        AuthToken authToken = this.applyToken(username, password, clientId, clientSecret);
        if(authToken == null){
            throw new RuntimeException();
        }
        //用户身份令牌
        String accessToken = authToken.getAccess_token();
        //存储到redis中的内容
        String jsonString = JSON.toJSONString(authToken);
        //将令牌存储到redis
        boolean result = this.saveToken(accessToken, jsonString, authProperties.getTokenValiditySeconds());
        if (!result) {
            throw new RuntimeException();
        }
        return authToken;

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

    /** 申请令牌 */
    private AuthToken applyToken(String username, String password, String clientId, String clientSecret){

        //令牌申请的地址 http://服务名/auth/oauth/token
        String authUrl = "http://" + ServiceConstants.OAUTH_SERVICE + "/oauth/token";
        //定义header
        LinkedMultiValueMap<String, String> header = new LinkedMultiValueMap<>();
        String httpBasic = getHttpBasic(clientId, clientSecret);
        header.add("Authorization",httpBasic);

        //定义body
        LinkedMultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type","password");
        body.add("username",username);
        body.add("password",password);
        body.add("redirect_uri","http://localhost");

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(body, header);

        //设置restTemplate远程调用时候，对400和401不让报错，正确返回数据
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler(){
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                if(response.getRawStatusCode()!= 400 && response.getRawStatusCode()!=401){
                    super.handleError(response);
                }
            }
        });

        ResponseEntity<Map> exchange = restTemplate.exchange(authUrl, HttpMethod.POST, httpEntity, Map.class);

        //申请令牌信息
        Map bodyMap = exchange.getBody();
        if(bodyMap == null ||
            bodyMap.get("access_token") == null ||
                bodyMap.get("refresh_token") == null ||
                bodyMap.get("jti") == null){
            return null;
        }
        AuthToken authToken = new AuthToken();
        //用户身份令牌
        authToken.setAccess_token((String) bodyMap.get("jti"));
        //刷新令牌
        authToken.setRefresh_token((String) bodyMap.get("refresh_token"));
        //jwt令牌
        authToken.setJwt_token((String) bodyMap.get("access_token"));
        return authToken;
    }

    /** 将令牌存储到cookie */
    private void saveCookie(String token){
        try{
            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
            CookieUtil.addCookie(response,authProperties.getCookieDomain(),"/","uid",token,authProperties.getCookieMaxAge(),false);
        }catch (NullPointerException e){
            e.printStackTrace();
        }

    }

    /** 获取http basic的串 */
    private String getHttpBasic(String clientId,String clientSecret){
        String string = clientId + ":" + clientSecret;
        //将串进行base64编码
        byte[] encode = Base64Utils.encode(string.getBytes());
        return "Basic "+ new String(encode);
    }
}
