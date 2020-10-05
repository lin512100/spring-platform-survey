package com.jtang.redisson.core;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 * redis配置类
 * @author lin512100
 * @date 2020/8/14
 */
@Slf4j
public class RedisHolder {

    /** Redisson 操作实体类 */
    private static final ConcurrentHashMap<String, RedissonClient> queryHelperMap = new ConcurrentHashMap<String, RedissonClient>();

    /** 维护组对应的配置实例 */
    private static final ConcurrentHashMap<String, RedisConfig> configMap = new ConcurrentHashMap<String, RedisConfig>();

    /** 默认组名 实例 */
    public final static String DEFAULT_REDIS_GROUP = "default";

    public RedisHolder (RedisConfig redisConfig){
        configMap.put(DEFAULT_REDIS_GROUP, redisConfig);
    }

    /**
     * 获取Redis 默认实例
     * @param database 数据库号
     * */
    public  RedissonClient getInstance(int database) {
        return getGroupInstance(DEFAULT_REDIS_GROUP, database);
    }

    /**
     * 获取自定义的Redis 实例
     * @param group 组名
     * @param database 数据库号
     * */
    public  synchronized RedissonClient getGroupInstance(String group, int database) {
        String key = group + "-" +database;
        if (!queryHelperMap.containsKey(key)) {
            if(!configMap.containsKey(group)){
                log.error("Redis 实例获取失败, 未配置该实例的信息,组名 = {}", group);
            }
            RedissonClient helper = (RedissonClient) Redisson.create(enhanceConfig(configMap.get(group).init(),database));
            queryHelperMap.put(key, helper);
        }
        return queryHelperMap.get(key);
    }

    /** 配置类增强 */
    private  Config enhanceConfig(Config config, int database){
        config.useSingleServer().setDatabase(database);
        return config;
    }

    /**
     * 添加Redis实例
     * @param group 组名
     * @param redisConfig 实例配置
     * */
    public synchronized  void addRedis(String group, RedisConfig redisConfig){
        configMap.put(group, redisConfig);
    }

}
