package com.jtang.oauth.config;

import com.jtang.redisson.core.RedisHolder;
import com.jtang.redisson.constants.RedisConstants;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
