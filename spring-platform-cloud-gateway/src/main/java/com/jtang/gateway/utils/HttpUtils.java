package com.jtang.gateway.utils;

import org.springframework.http.server.reactive.ServerHttpRequest;

import java.util.List;
import java.util.Objects;

/**
 * 网络工具类
 * @date 2020/7/5 13:12
 * @author LinJinTang
 */
public class HttpUtils {

    public static String getRemoteHost(ServerHttpRequest request) {
        String ip = getHeader(request, "x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = getHeader(request, "Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = getHeader(request, "WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            try {
                 ip = Objects.requireNonNull(request.getRemoteAddress()).getAddress().getHostAddress();
                ip =  ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
            } catch (Exception ignore) {}
        }
        return ip;
    }

    private static String getHeader(ServerHttpRequest request, String header){
        List<String> head = request.getHeaders().get(header);
        if(head == null || head.size() == 0){
            return null;
        }
        return head.get(0);
    }
}
