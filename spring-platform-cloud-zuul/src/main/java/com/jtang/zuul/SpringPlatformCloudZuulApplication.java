package com.jtang.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author jtang
 * @date 2020/4/16 10:30
 */
@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class SpringPlatformCloudZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringPlatformCloudZuulApplication.class, args);
    }

}
