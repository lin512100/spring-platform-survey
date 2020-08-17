package com.jtang.zuul.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


/**
 * 资源服务配置
 * @author lin512100
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
