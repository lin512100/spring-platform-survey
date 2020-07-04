package com.jtang.oauth.utils;

import com.alibaba.fastjson.JSON;
import com.jtang.common.model.oauth.AuthToken;
import com.jtang.common.utils.CookieUtil;
import com.jtang.common.utils.HttpUtils;
import com.jtang.oauth.properties.AuthProperties;
import org.redisson.api.RedissonClient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Auth服务操作cookie
 * @date 2020/7/4 12:00
 * @author LinJinTang
 */
public class AuthCookieUtils {
    /** 缓存到前端cookie 的名字 */
    private final static String COOKIE_NAME = "uid";

    /** 将令牌存储到cookie */
    public static void saveCookie(String token, AuthProperties authProperties){
        HttpServletResponse response = HttpUtils.getResponse();
        CookieUtil.addCookie(response,authProperties.getCookieDomain(),"/",COOKIE_NAME,token,authProperties.getCookieMaxAge(),false);
    }

    /** 从redis查询令牌 */
    public static AuthToken getUserToken(RedissonClient redissonClient,String token){
        String key = "user_token:" + token;
        // 从redis中取到令牌信息
        String value = redissonClient.getBucket(key).get().toString();
        // 转成对象
        try {
            return JSON.parseObject(value, AuthToken.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /** 取出cookie中的身份令牌 */
    public static String getTokenFormCookie(){
        HttpServletRequest request = HttpUtils.getRequest();
        Map<String, String> map = CookieUtil.readCookie(request, COOKIE_NAME);
        if(!map.isEmpty() && map.get(COOKIE_NAME)!=null){
            return map.get(COOKIE_NAME);
        }
        return null;
    }

    /** 从cookie删除token */
    public static void clearCookie(String token, String cookieDomain){
        HttpServletResponse response = HttpUtils.getResponse();
        //HttpServletResponse response,String domain,String path, String name, String value, int maxAge,boolean httpOnly
        CookieUtil.addCookie(response,cookieDomain,"/",COOKIE_NAME, token,0,false);
    }

    /** 删除token */
    public static boolean delToken(RedissonClient redissonClient, String accessToken){
        String key = "user_token:" + accessToken;
        redissonClient.getBucket(key).delete();
        return true;
    }

}
