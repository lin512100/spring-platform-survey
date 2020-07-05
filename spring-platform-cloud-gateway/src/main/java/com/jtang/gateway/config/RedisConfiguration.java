package com.jtang.gateway.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Redis配置类
 * @date 2020/7/4 23:59
 * @author LinJinTang
 */
@Configuration
public class RedisConfiguration {

    @Autowired
    private RedisProperties redisProperties;

    /** 默认redis数据库 */
    private static final int DEFAULT_REDIS_DATABASE = 0;


    /** Redission 配置*/
    @Bean
    public RedissonClient redissonClient(){
        Config config = new Config();
        String redisUrl = String.format("redis://%s:%s",redisProperties.getHost()+"",redisProperties.getPort()+"");
        config.useSingleServer().setAddress(redisUrl).setPassword(redisProperties.getPassword());
        config.useSingleServer().setDatabase(DEFAULT_REDIS_DATABASE);
        return Redisson.create(config);
    }
}
