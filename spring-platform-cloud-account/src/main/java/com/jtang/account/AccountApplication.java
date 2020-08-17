package com.jtang.account;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author lin512100
 * @date 2020/4/15 14:25
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
@MapperScan(basePackages = "com.jtang.*.mapper")
public class AccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }

}
