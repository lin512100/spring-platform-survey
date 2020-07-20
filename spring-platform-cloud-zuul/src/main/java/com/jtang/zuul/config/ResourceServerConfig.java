package com.jtang.zuul.config;

import com.jtang.base.client.PublicUrlConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * 资源服务配置
 * @author linjt
 * 放行所有链接地址
 */
@Slf4j
@Configuration
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {
    /**
     * Http安全配置，对每个到达系统的http请求链接进行校验
     * */
    @Override
    public void configure(WebSecurity web) throws Exception {
        // 网页校验忽略地址
        web.ignoring().antMatchers("/**");
    }

}
