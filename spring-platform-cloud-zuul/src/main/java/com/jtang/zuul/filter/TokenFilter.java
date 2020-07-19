package com.jtang.zuul.filter;

import com.jtang.base.utils.PathMatcherUtil;
import com.jtang.zuul.rule.Roster;
import com.jtang.zuul.service.AuthService;
import com.jtang.zuul.utils.RequestUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;

/**
 * 网页管理拦截器
 * @date 2020/7/18 19:35
 * @author LinJinTang
 */
@Slf4j
public class TokenFilter extends ZuulFilter {

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

        log.info("send {} request to {}", request.getMethod(), RequestUtils.getServletPath());

        // 判断是否为放行链接
        boolean addrStatus = PathMatcherUtil.matches(Roster.WHITE_ADDR,request.getRequestURI());
        if(addrStatus){
            return null;
        }

        //取cookie中的身份令牌
        String tokenFromCookie = authService.getTokenFromCookie(request);
        if(StringUtils.isEmpty(tokenFromCookie)){
            return refused(ctx,"uid is empty");
        }

        //从header中取jwt
        String jwtFromHeader = authService.getJwtFromHeader(request);
        if(StringUtils.isEmpty(jwtFromHeader)){
            //拒绝访问
            return refused(ctx,"token is empty");
        }
        return  null;
    }

    private RequestContext refused (RequestContext ctx,String message){
        log.warn(message);
        //过滤该请求，不往下级服务去转发请求，到此结束
        ctx.setSendZuulResponse(false);
        ctx.setResponseStatusCode(401);
        ctx.setResponseBody("{\"result\":\""+message+"!\"}");
        ctx.getResponse().setContentType("text/html;charset=UTF-8");
        return ctx;
    }
}
