package com.jtang.feign.config;

import com.jtang.feign.service.InitService;
import com.jtang.feign.service.impl.InitServiceImpl;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @date 2020/7/5 16:12
 * @author LinJinTang
 */
@Configuration
@EnableFeignClients(basePackages={"com.jtang.feign.service"})
public class FeignConfiguration {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /** 注册初始化服务 */
    @Bean
    public InitService initService(){
        return new InitServiceImpl();
    }
}
