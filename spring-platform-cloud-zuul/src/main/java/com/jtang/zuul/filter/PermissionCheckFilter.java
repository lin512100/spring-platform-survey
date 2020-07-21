package com.jtang.zuul.filter;

import com.alibaba.fastjson.JSONObject;
import com.jtang.base.client.InnerUrlConstants;
import com.jtang.base.client.PublicUrlConstants;
import com.jtang.base.utils.PathMatcherUtil;
import com.jtang.common.model.account.response.HandleAllow;
import com.jtang.feign.model.UserDao;
import com.jtang.feign.model.UserJwt;
import com.jtang.feign.service.UserApiService;
import com.jtang.zuul.service.AuthService;
import com.jtang.zuul.service.RoleService;
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
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * 链接权限校验器
 * @author linjt
 * @date 2020/7/20
 */
@Slf4j
@Service
public class PermissionCheckFilter extends ZuulFilter {

    @Autowired
    private AuthService authService;

    @Autowired
    private RoleService roleService;

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
        if(servletPath.contains(PublicUrlConstants.PUBLIC_PRE)){
            return null;
        }

        // 包含DEFAULT_REFUSE字段的拒绝访问
        if(servletPath.contains(InnerUrlConstants.INNER_PRE)){
            return ZuulDealUtils.refused(ctx, "request url can't be to visit ");
        }

        // 解析JWT里面包含的用户角色字段
        String jwt = authService.getJwtFromHeader(request);
        Jwt decode = JwtHelper.decode(jwt);
        // 获取内容信息
        JSONObject jsonObject = JSONObject.parseObject(decode.getClaims());
        String authorities = jsonObject.get("authorities").toString();
        String roleIds = authorities.substring(1,authorities.length() - 1).replace("\"","");

        // 超级管理员权限
        if(Integer.parseInt(jsonObject.get("id").toString()) == 1){
            return null;
        }

        // 匹配出调用的是什么服务
        String url = RequestUtils.getServletPath();
        String server = Objects.requireNonNull(url).split("/")[1];

        // 校验链接权限
        Set<HandleAllow> operator = roleService.getRoleItem(roleIds,server);
        for (HandleAllow handleAllow : operator){
            if(PathMatcherUtil.match("/"+ server + handleAllow.getUrl(), url) && request.getMethod().equals(handleAllow.getMethod())){
                return null;
            }
        }
        return ZuulDealUtils.refused(ctx,"Permission denied can't not visit:" + request.getRequestURI());
    }
}
