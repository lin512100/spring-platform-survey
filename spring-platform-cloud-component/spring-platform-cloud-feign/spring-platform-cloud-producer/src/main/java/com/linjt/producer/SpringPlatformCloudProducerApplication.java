package com.jtang.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jtang
 * @date 2020/4/15 10:21
 */
@RestController
@SpringBootApplication
@EnableDiscoveryClient
public class SpringPlatformCloudProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringPlatformCloudProducerApplication.class, args);
    }

    @RequestMapping("/hello")
    public String hello(@RequestParam String name) {
        return "hello "+name+"，producer is ready";
    }

}
