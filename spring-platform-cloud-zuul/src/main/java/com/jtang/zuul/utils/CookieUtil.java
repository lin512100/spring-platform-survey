package com.jtang.zuul.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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
    public static Map<String,String> readCookie(HttpServletRequest request, String ... cookieNames) {
        Map<String,String> cookieMap = new HashMap<String,String>(4);
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if(cookie != null){
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
        return cookieMap;
    }
}
