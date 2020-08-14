package com.jtang.redisson.config;

import com.jtang.redisson.core.DefaultRedisConfig;
import com.jtang.redisson.core.RedisConfig;
import com.jtang.redisson.core.RedisHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Redi相关组价自动配置
 * @author lin512100
 * @date 2020/8/14
 */
@Slf4j
@Configuration
public class AutoRedisConfiguration {

    public AutoRedisConfiguration(){
        // 添加默认的实例
        log.info("组价默认初始化->添加默认的实例");
        RedisHolder.addRedis(RedisHolder.DEFAULT_REDIS_GROUP, redisConfig());
    }

    @Bean
    @ConditionalOnMissingBean
    public RedisConfig redisConfig(){
        return new DefaultRedisConfig();
    }
}
