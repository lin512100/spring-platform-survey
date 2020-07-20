package com.jtang.zuul.filter;

import com.jtang.base.client.ZuulRuleConstants;
import com.jtang.zuul.utils.RequestUtils;
import com.jtang.zuul.utils.ZuulDealUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;

/**
 * 链接权限校验器
 * @author linjt
 * @date 2020/7/20
 */
@Slf4j
public class PermissionCheckFilter extends ZuulFilter {

    @Autowired
    private RedissonClient redissonClient;



    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        // 请求地址
        String servletPath = RequestUtils.getServletPath();
        // 请求方法
        String method = request.getMethod();

        if(StringUtils.isEmpty(servletPath) || StringUtils.isEmpty(method)){
            return ZuulDealUtils.refused(ctx,"request url and method can't empty");
        }
        // 包含DEFAULT_PASS字段的放行
        if(servletPath.contains(ZuulRuleConstants.DEFAULT_PASS)){
            return null;
        }

        // 包含DEFAULT_REFUSE字段的拒绝访问
        if(servletPath.contains(ZuulRuleConstants.DEFAULT_REFUSE)){
            return ZuulDealUtils.refused(ctx, "request url can't be to visit ");
        }


        // 链接权限校验


        return null;
    }
}
