package com.jtang.web.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis分页拦截器
 * @author linjt
 * @date 2020/7/9
 */
@Configuration
public class MybatisConfig {

    @Bean
    @ConditionalOnMissingBean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
