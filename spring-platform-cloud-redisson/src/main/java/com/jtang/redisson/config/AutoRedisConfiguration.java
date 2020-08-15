package com.jtang.redisson.config;

import com.jtang.redisson.core.DefaultRedisConfig;
import com.jtang.redisson.core.RedisConfig;
import com.jtang.redisson.core.RedisHolder;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.HashMap;
import java.util.Map;

import static com.jtang.redisson.constants.RedisConstants.REDIS_DATABASE_DEFAULT;

/**
 * Redi相关组价自动配置
 * @author lin512100
 * @date 2020/8/14
 */
@Slf4j
@EnableCaching
@Configuration
public class AutoRedisConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public RedisConfig redisConfig(){
        // 添加默认的实例
        log.info("组件默认初始化->添加默认组实例");
        return new DefaultRedisConfig();
    }

    @Bean
    public RedisHolder redisHolder(RedisConfig redisConfig){
        return new RedisHolder(redisConfig);
    }

    /** 默认实例 */
    @Bean
    @ConditionalOnMissingBean
    public RedissonClient redissonClient(RedisHolder redisHolder){
        return redisHolder.getInstance(REDIS_DATABASE_DEFAULT);
    }

    @Bean
    CacheManager cacheManager(RedissonClient redissonClient) {
        Map<String, CacheConfig> config = new HashMap<String, CacheConfig>(8);
        // 创建一个名称为"defaultCache"的缓存，过期时间ttl为24分钟，同时最长空闲时maxIdleTime为12分钟。
        config.put("defaultCache", new CacheConfig(24*60*1000, 12*60*1000));
        return new RedissonSpringCacheManager(redissonClient, config);
    }
}
