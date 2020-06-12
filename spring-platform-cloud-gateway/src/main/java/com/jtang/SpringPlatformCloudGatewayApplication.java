package com.jtang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 网关服务
 * @author linjt
 * @date 2020/6/12
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SpringPlatformCloudGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringPlatformCloudGatewayApplication.class, args);
    }

}
