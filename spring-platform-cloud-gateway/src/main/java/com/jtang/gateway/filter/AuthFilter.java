package com.jtang.gateway.filter;

import com.jtang.base.utils.PathMatcherUtil;
import com.jtang.gateway.rule.Roster;
import com.jtang.gateway.service.AuthService;
import com.jtang.gateway.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Auth 拦截
 * @author linjt
 * @date 2020/6/12
 */

@Slf4j
@Component
public class AuthFilter implements GlobalFilter, Ordered {

    @Autowired
    private AuthService authService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        //得到request
        ServerHttpRequest request = exchange.getRequest();

        // 访问黑名单

        // 放通地址
        boolean addrStatus = PathMatcherUtil.matches(Roster.WHITE_ADDR,request.getURI().getPath());
        if(addrStatus){
            return chain.filter(exchange);
        }

        //取cookie中的身份令牌
        String tokenFromCookie = authService.getTokenFromCookie(request);
        if(StringUtils.isEmpty(tokenFromCookie)){
            return accessDenied(exchange);
        }

        //从header中取jwt
        String jwtFromHeader = authService.getJwtFromHeader(request);
        if(StringUtils.isEmpty(jwtFromHeader)){
            //拒绝访问
            return exchange.getResponse().setComplete();

        }
//        //JWT已经过期吗
//        if(authService.isExpire(tokenFromCookie)){
//            log.info("JWT已经过期："+tokenFromCookie);
//            return accessDenied(exchange);
//        }
        return chain.filter(exchange);
    }

    /** 拒绝访问 */
    private Mono<Void> accessDenied(ServerWebExchange exchange){
        exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
        return exchange.getResponse().setComplete();
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
