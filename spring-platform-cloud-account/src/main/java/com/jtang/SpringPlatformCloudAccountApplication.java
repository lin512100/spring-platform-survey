package com.jtang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @date 2020/4/18 11:50
 * @author LinJinTang
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.jtang.*.mapper")
public class SpringPlatformCloudAccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringPlatformCloudAccountApplication.class);
    }
}
