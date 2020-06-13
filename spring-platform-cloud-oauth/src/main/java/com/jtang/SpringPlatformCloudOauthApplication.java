package com.jtang;

import com.jtang.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @date 2020/6/13 0:13
 * @author LinJinTang
 */
@EnableFeignClients
@SpringCloudApplication
public class SpringPlatformCloudOauthApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringPlatformCloudOauthApplication.class, args);
    }

}
