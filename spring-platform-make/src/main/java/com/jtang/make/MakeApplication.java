package com.jtang.make;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 终端应用
 * @author lin512100
 * @date 2020/9/3
 */
@SpringBootApplication
@MapperScan(basePackages = "com.jtang.*.mapper")
public class MakeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MakeApplication.class, args);
    }
}
