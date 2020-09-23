package com.jtang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author huanglian
 * @date 2020/8/26
 * @description nacos注册中心
 */
@EnableDiscoveryClient
@SpringBootApplication(exclude={
        DataSourceAutoConfiguration.class,
//        HibernateJpaAutoConfiguration.class, //（如果使用Hibernate时，需要加）
        DataSourceTransactionManagerAutoConfiguration.class,
})
public class NacosApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosApplication.class, args);

    }

}
