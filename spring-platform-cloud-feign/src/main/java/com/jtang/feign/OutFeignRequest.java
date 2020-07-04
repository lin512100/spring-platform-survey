package com.jtang.feign;

import com.jtang.common.utils.HttpUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


@Component
public class OutFeignRequest implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        HttpServletRequest request = HttpUtils.getRequest();
        //添加token
        requestTemplate.header("Authorization", request.getHeader("Authorization"));
    }
}
