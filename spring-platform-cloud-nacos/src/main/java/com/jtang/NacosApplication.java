package com.jtang;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @version 1.0
 **/
@SpringBootApplication
@RestController
public class NacosApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosApplication.class,args);
    }

//    @Autowired
//    ConfigurableApplicationContext applicationContext;
    //通过value注解读取配置信息
    @Value("${common.name}")
    private String config1;

    @GetMapping("/configs")
    public String getConfigs(){
        //读取配置信息
        return config1;
    }

}
