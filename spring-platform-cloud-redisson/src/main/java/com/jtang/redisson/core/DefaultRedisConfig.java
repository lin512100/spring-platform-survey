package com.jtang.redisson.core;

import lombok.extern.slf4j.Slf4j;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Redis 默认配置
 * @author lin512100
 * @date 2020/8/14
 */
@Slf4j
public class DefaultRedisConfig implements RedisConfig {

    @Autowired
    private RedisProperties redisProperties;

    @Override
    public Config init() {
        Config config = new Config();
        try{
            String redisUrl = String.format("redis://%s:%s",redisProperties.getHost()+"",redisProperties.getPort()+"");
            config.useSingleServer().setAddress(redisUrl).setPassword(redisProperties.getPassword()).setTimeout(1000)
                    .setRetryAttempts(3)
                    .setRetryInterval(1000)
                    .setPingConnectionInterval(1000);
        }catch (Exception e){
            log.error("Redis 数据库配置异常："+ e.getMessage());
            e.printStackTrace();
        }
        return config;
    }
}
