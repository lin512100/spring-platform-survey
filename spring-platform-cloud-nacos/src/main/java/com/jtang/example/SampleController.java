package com.jtang.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * @author huanglian
 * @date 2020/8/26
 * @description nacos示例详情参见官方文档
 * @see <a href="https://github.com/alibaba/spring-cloud-alibaba/blob/master/spring-cloud-alibaba-examples/nacos-example/nacos-config-example/readme-zh.md">nacos中文文档</a>
 */
@RefreshScope
public class SampleController {
    @Value("${user.name}")
    String userName;

    @Value("${user.age}")
    int age;
}
