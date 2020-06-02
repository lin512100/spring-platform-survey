package com.jtang.configclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author jtang
 * @date 2020/4/15 16:35
 */
@RestController
@SpringBootApplication
@RefreshScope
public class SpringPlatformCloudConfigClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringPlatformCloudConfigClientApplication.class, args);
    }

    @Value("${springcloud.hello}")
    private String test;

    @RequestMapping("/test")
    public String test(){
        return test;
    }

}
