package com.jtang.feign;

import com.jtang.feign.enums.AuthMode;
import com.jtang.feign.service.FeignService;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

/**
 * 内部feign调用配置器
 * @date 2020/7/4 13:45
 * @author LinJinTang
 */
@Slf4j
@Component
public class InnerFeignRequest implements RequestInterceptor {

    @Autowired
    private FeignService feignService;

    private static final String AUTHORIZATION_HEADER = "Authorization";

    private static final String BEARER_TOKEN_TYPE = "Bearer";

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header(AUTHORIZATION_HEADER, String.format("%s %s", BEARER_TOKEN_TYPE,  feignService.getTokenByClientCredentials()));
    }


}
