package com.jtang.base.client;

import java.util.HashMap;

/**
 * Redis库设置
 * @date 2020/7/5 14:37
 * @author LinJinTang
 */
public class RedisConstants {

    /** 共享数据 服务 */
    public final static int REDIS_DATABASE_SHARE = 0;

    /** 授权服务 */
    public final static int REDIS_DATABASE_OAUTH = 1;

    /** 用户服务 */
    public final static int REDIS_DATABASE_USER = 2;

    /** 网关服务 */
    public final static int REDIS_DATABASE_ZUUL = 3;


    public static HashMap<String,Integer> getDataBase(){
        HashMap<String, Integer> database = new HashMap<>();
        database.put("REDIS_DATABASE_SHARE", REDIS_DATABASE_SHARE);
        database.put("REDIS_DATABASE_OAUTH", REDIS_DATABASE_OAUTH);
        database.put("REDIS_DATABASE_USER", REDIS_DATABASE_OAUTH);
        database.put("REDIS_DATABASE_ZUUL", REDIS_DATABASE_OAUTH);
        return database;
    }
}
