package com.jtang.oauth.config;

import com.jtang.redisson.config.RedisHolder;
import com.jtang.redisson.constants.RedisConstants;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @date 2020/7/1 21:38
 * @author LinJinTang
 */
@Configuration
public class RedisConfig {

    /** Redission 配置*/
    @Bean
    public RedissonClient redissonClient(){
        return RedisHolder.getInstance(RedisConstants.REDIS_DATABASE_OAUTH);
    }
}
