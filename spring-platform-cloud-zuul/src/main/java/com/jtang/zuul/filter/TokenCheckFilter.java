package com.jtang.zuul.filter;

import com.jtang.base.utils.PathMatcherUtil;
import com.jtang.feign.utils.IpUtils;
import com.jtang.zuul.rule.Roster;
import com.jtang.zuul.service.AuthService;
import com.jtang.zuul.utils.RequestUtils;
import com.jtang.zuul.utils.ZuulDealUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;

/**
 * Auth权限拦截器
 * @date 2020/7/18 19:35
 * @author LinJinTang
 */
@Slf4j
public class TokenCheckFilter extends ZuulFilter {

    @Autowired
    private AuthService authService;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        log.info("{} send {} request to {}", IpUtils.getIpAddr(request), request.getMethod(), RequestUtils.getServletPath());

        //TODO 判断是否是IP黑名单

        // 判断是否为放行链接
        boolean addrStatus = PathMatcherUtil.matches(Roster.WHITE_ADDR,RequestUtils.getServletPath());
        if(addrStatus){
            return null;
        }

        //取cookie中的身份令牌
        String tokenFromCookie = authService.getTokenFromCookie(request);
        if(StringUtils.isEmpty(tokenFromCookie)){
            return ZuulDealUtils.refused(ctx,"uid is empty");
        }

        //从header中取jwt
        String jwt = authService.getJwtFromHeader(request);
        if(StringUtils.isEmpty(jwt)){
            //拒绝访问
            return ZuulDealUtils.refused(ctx,"token is empty");
        }
        return  null;
    }
}
