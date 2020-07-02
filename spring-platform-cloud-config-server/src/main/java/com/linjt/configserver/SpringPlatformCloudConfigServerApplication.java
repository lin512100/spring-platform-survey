package com.linjt.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author jtang
 * @date 2020/4/15 14:25
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SpringPlatformCloudConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringPlatformCloudConfigServerApplication.class, args);
    }

}
