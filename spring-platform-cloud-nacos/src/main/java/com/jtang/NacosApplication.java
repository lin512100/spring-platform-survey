package com.jtang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @version 1.0
 **/
@SpringBootApplication(exclude={
        DataSourceAutoConfiguration.class,
//        HibernateJpaAutoConfiguration.class, //（如果使用Hibernate时，需要加）
        DataSourceTransactionManagerAutoConfiguration.class,
})
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
