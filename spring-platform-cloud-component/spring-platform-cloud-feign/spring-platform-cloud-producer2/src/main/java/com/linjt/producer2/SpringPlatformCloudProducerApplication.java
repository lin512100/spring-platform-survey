package com.jtang.producer2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author jtang
 * @date 2020/4/15 10:21
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SpringPlatformCloudProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringPlatformCloudProducerApplication.class, args);
    }

}
