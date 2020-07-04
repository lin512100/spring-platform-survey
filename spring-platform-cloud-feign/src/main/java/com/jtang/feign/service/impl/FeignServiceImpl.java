package com.jtang.feign.service.impl;

import com.jtang.common.client.ServiceConstants;
import com.jtang.common.enums.HttpStatusEnum;
import com.jtang.common.exception.ExceptionCast;
import com.jtang.common.model.oauth.AuthToken;
import com.jtang.common.model.oauth.response.AuthCode;
import com.jtang.feign.enums.AuthMode;
import com.jtang.feign.properties.AuthProperties;
import com.jtang.feign.service.FeignService;
import com.jtang.feign.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;

/**
 * Token获取
 * @date 2020/7/4 15:30
 * @author LinJinTang
 */
@Slf4j
@Service
public class FeignServiceImpl implements FeignService {

    @Autowired
    private AuthProperties authProperties;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public AuthToken getTokenByPassword(String username, String password) {
        String authUrl = "http://" + ServiceConstants.OAUTH_SERVICE + "/oauth/token";

        //定义header
        LinkedMultiValueMap<String, String> header = new LinkedMultiValueMap<>();

        //定义body
        LinkedMultiValueMap<String, String> body = new LinkedMultiValueMap<>();
            body.add("username",username);
            body.add("password",password);
            body.add("grant_type", AuthMode.PASSWORD.getMode() +"");
            body.add("redirect_uri","http://localhost");

        //申请令牌信息
        Map bodyMap = submit(authUrl, body, header).getBody();
        if(bodyMap == null ||
                bodyMap.get("access_token") == null ||
                bodyMap.get("refresh_token") == null ||
                bodyMap.get("jti") == null){
            ExceptionCast.cast(AuthCode.AUTH_LOGIN_APPLY_TOKEN_FAIL);
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

    @Override
    public String getTokenByClientCredentials() {
        //令牌申请的地址 http://服务名/auth/oauth/token
        String authUrl = "http://" + ServiceConstants.OAUTH_SERVICE + "/oauth/token" + "?grant_type=" +  AuthMode.CLIENT_CREDENTIALS.getMode();

        //定义header
        LinkedMultiValueMap<String, String> header = new LinkedMultiValueMap<>();

        //申请令牌信息
        Map bodyMap = submit(authUrl, null, header).getBody();
        if(bodyMap == null || bodyMap.get("access_token") == null){
            ExceptionCast.cast(AuthCode.AUTH_LOGIN_APPLY_TOKEN_FAIL);
        }
        //jwt令牌
        return (String) bodyMap.get("access_token");
    }

    private ResponseEntity<Map> submit(String authUrl, LinkedMultiValueMap<String, String> body, LinkedMultiValueMap<String, String> header){
        //定义header
        String httpBasic = HttpUtils.getHttpBasic(authProperties.getClientId(), authProperties.getClientSecret());
        header.add("Authorization",httpBasic);

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(body, header);
        //设置restTemplate远程调用时候，对400和401不让报错，正确返回数据
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler(){
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                if(response.getRawStatusCode() != HttpStatusEnum.BAD_REQUEST.code()
                        && response.getRawStatusCode() != HttpStatusEnum.UNAUTHORIZED.code()){
                    super.handleError(response);
                }
            }
        });
        return  restTemplate.exchange(authUrl, HttpMethod.POST, httpEntity, Map.class);
    }

}
