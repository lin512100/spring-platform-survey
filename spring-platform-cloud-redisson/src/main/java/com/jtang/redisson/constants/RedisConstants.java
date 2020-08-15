package com.jtang.redisson.constants;

/**
 * Redis库设置
 * @date 2020/7/5 14:37
 * @author LinJinTang
 */
public class RedisConstants {

    /** 默认数据缓存 */
    public final static int REDIS_DATABASE_DEFAULT =  0;

    /** 授权服务 */
    public final static int REDIS_DATABASE_OAUTH = 1;

    /** 用户服务 */
    public final static int REDIS_DATABASE_USER = 2;

    /** 网关服务 */
    public final static int REDIS_DATABASE_ZUUL = 3;
}
