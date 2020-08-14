package com.jtang.account.config;

import com.jtang.redisson.config.RedisHolder;
import com.jtang.redisson.constants.RedisConstants;
import org.redisson.api.RedissonClient;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Redis配置
 * @date 2020/7/5 14:23
 * @author LinJinTang
 */
@EnableCaching
@Configuration
public class RedisConfig {

    @Autowired
    private RedisProperties redisProperties;

    /** Redisson 配置*/
    @Bean
    public RedissonClient redissonClient(){
        return RedisHolder.getInstance(RedisConstants.REDIS_DATABASE_OAUTH);
    }

    @Bean
    CacheManager cacheManager(RedissonClient redissonClient) {
        Map<String, CacheConfig> config = new HashMap<String, CacheConfig>(8);
        // 创建一个名称为"testMap"的缓存，过期时间ttl为24分钟，同时最长空闲时maxIdleTime为12分钟。
        config.put("redisCache", new CacheConfig(24*60*1000, 12*60*1000));
        return new RedissonSpringCacheManager(redissonClient, config);
    }
}
