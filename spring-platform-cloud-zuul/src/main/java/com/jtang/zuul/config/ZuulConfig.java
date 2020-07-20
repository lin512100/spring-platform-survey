package com.jtang.zuul.config;

import com.jtang.zuul.filter.TokenCheckFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 过滤器配置
 * @date 2020/7/19 12:19
 * @author LinJinTang
 */
@Configuration
public class ZuulConfig {

    @Bean
    public TokenCheckFilter tokenFilter(){
        return new TokenCheckFilter();
    }
}
