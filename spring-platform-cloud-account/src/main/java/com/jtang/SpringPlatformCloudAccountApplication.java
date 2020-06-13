package com.jtang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 账户服务
 * @date 2020/6/12 22:48
 * @author LinJinTang
 */
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan(basePackages = "com.jtang.*.mapper")
public class SpringPlatformCloudAccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringPlatformCloudAccountApplication.class, args);
    }

}
