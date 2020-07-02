package com.jtang.common.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @date 2020/7/2 21:16
 * @author LinJinTang
 */
public class CookieUtil {

    /**
     * 设置cookie
     *
     * @param response 响应体
     * @param name     cookie名字
     * @param value    cookie值
     * @param maxAge   cookie生命周期 以秒为单位
     */
    public static void addCookie(HttpServletResponse response,String domain,String path, String name,
                                 String value, int maxAge,boolean httpOnly) {
        Cookie cookie = new Cookie(name, value);
        cookie.setDomain(domain);
        cookie.setPath(path);
        cookie.setMaxAge(maxAge);
        cookie.setHttpOnly(httpOnly);
        response.addCookie(cookie);
    }



    /**
     * 根据cookie名称读取cookie
     * @param request 请求体
     * @param cookieNames,cookieName2 cookie名字
     * @return map<cookieName,cookieValue>
     */

    public static Map<String,String> readCookie(HttpServletRequest request,String ... cookieNames) {
        Map<String,String> cookieMap = new HashMap<String,String>(4);
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    String cookieName = cookie.getName();
                    String cookieValue = cookie.getValue();
                    for (String name : cookieNames) {
                        if (name.equals(cookieName)) {
                            cookieMap.put(cookieName, cookieValue);
                        }
                    }
                }
            }
        return cookieMap;
    }
}
