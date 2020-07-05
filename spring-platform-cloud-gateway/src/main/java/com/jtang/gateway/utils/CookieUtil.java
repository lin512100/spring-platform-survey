package com.jtang.gateway.utils;

import org.springframework.http.HttpCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Cookie操作类
 * @date 2020/7/4 23:52
 * @author LinJinTang
 */
public class CookieUtil {

    /**
     * 根据cookie名称读取cookie
     * @param request 请求体
     * @param cookieNames,cookieName2 cookie名字
     * @return map<cookieName,cookieValue>
     */
    public static Map<String,String> readCookie(ServerHttpRequest request, String ... cookieNames) {
        Map<String,String> cookieMap = new HashMap<String,String>(4);
        MultiValueMap<String, HttpCookie> cookies = request.getCookies();
        if (!cookies.isEmpty()) {
            for (String key : cookies.keySet()) {
                List<HttpCookie> httpCookies = cookies.get(key);
                if(httpCookies != null){
                    for (HttpCookie cookie: httpCookies){
                        String cookieName = cookie.getName();
                        String cookieValue = cookie.getValue();
                        for (String name : cookieNames) {
                            if (name.equals(cookieName)) {
                                cookieMap.put(cookieName, cookieValue);
                            }
                        }
                    }
                }

            }
        }
        return cookieMap;
    }
}
