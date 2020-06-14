package com.jtang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @date 2020/6/13 0:13
 * @author LinJinTang
 */
@EnableFeignClients
@SpringCloudApplication
@MapperScan(basePackages = "com.jtang.*.mapper")
public class SpringPlatformCloudOauthApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringPlatformCloudOauthApplication.class, args);
    }

}
