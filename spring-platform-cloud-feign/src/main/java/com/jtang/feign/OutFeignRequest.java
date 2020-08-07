package com.jtang.feign;

import com.jtang.common.utils.HttpRequestUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 外部调用
 * @date 2020/7/4 19:12
 * @author LinJinTang
 */
@Component
public class OutFeignRequest implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        HttpServletRequest request = HttpRequestUtils.getRequest();
        //添加token
        requestTemplate.header("Authorization", request.getHeader("Authorization"));
    }
}
