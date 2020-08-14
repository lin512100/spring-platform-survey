package com.jtang.redisson.core;

import org.redisson.config.Config;

/**
 * Redis配置
 * @author lin512100
 * @date 2020/8/14
 */
public interface RedisConfig {

    /** 指定Redis 配置 */
    Config init();
}
