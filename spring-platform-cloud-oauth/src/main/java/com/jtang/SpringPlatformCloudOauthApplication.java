package com.jtang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @date 2020/6/13 0:13
 * @author LinJinTang
 */
@SpringCloudApplication
@MapperScan(basePackages = "com.jtang.*.mapper")
public class SpringPlatformCloudOauthApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringPlatformCloudOauthApplication.class, args);
    }

}
