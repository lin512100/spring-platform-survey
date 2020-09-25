package com.jtang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @version 1.0
 **/
@RestController
@SpringBootApplication
public class NacosApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosApplication.class,args);
    }

//    @Value("${common.name}")
//    private String config1;
//
//    @GetMapping("/configs")
//    public String getConfigs(){
//        //读取配置信息
//        return config1;
//    }

}
