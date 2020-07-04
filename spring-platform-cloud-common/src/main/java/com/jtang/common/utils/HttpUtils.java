package com.jtang.common.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 网络请求工具类
 * @date 2020/7/4 12:21
 * @author LinJinTang
 */
public class HttpUtils {

    /** 获取请求响应体 */
    public static HttpServletResponse getResponse(){
        HttpServletResponse response = getServletRequestAttributes().getResponse();
        if(response == null){
            throw new RuntimeException();
        }
        return response;
    }

    /** 获取请求响应体 */
    public static HttpServletRequest getRequest(){
        return getServletRequestAttributes().getRequest();
    }

    /** 获取网络请求体 */
    private static ServletRequestAttributes getServletRequestAttributes(){
        ServletRequestAttributes servletRequestAttributes = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
        if(servletRequestAttributes == null){
            throw new RuntimeException();
        }
        return servletRequestAttributes;
    }


}
