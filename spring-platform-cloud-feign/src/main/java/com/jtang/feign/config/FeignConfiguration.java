package com.jtang.feign.config;

import com.jtang.feign.service.FeignService;
import com.jtang.feign.service.impl.FeignServiceImpl;
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

    @Bean
    public FeignService feignService(){
        return new FeignServiceImpl();
    }

}
