package com.jtang.zuul.utils;

import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;

/**
 * 网关处理工具类
 * @author lin512100
 * @date 2020/7/20
 */
@Slf4j
public class ZuulDealUtils {

    public static RequestContext refused (RequestContext ctx, String message){
        log.warn(message);
        //过滤该请求，不往下级服务去转发请求，到此结束
        ctx.setSendZuulResponse(false);
        ctx.setResponseStatusCode(401);
        ctx.setResponseBody("{\"result\":\""+message+"!\"}");
        ctx.getResponse().setContentType("text/html;charset=UTF-8");
        return ctx;
    }
}
