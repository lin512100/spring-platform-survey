package com.jtang.zuul.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 请求工具类
 * @date 2020/7/19 12:27
 * @author LinJinTang
 */
public class RequestUtils {
    private final static String ServerPort = "80";
    /**
     * 获取request对象
     * @return request
     */
    public static HttpServletRequest getRequest() {
        return((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
                .getRequest();
    }
    /**
     * 项目的真实路径
     * @return String
     */
    public static String getPjoPath() {
        try {
            return StringUtils.replace(getRequest().getServletContext().getContextPath(), "/", "\\");
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * 获取客户端请求的路径名，如：/object/delObject
     * @return String
     */
    public static String getServletPath() {
        try {
            return getRequest().getServletPath();
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * 获取服务器地址，如：localhost
     * @return String
     */
    public static String getServerName() {
        try {
            return getRequest().getServerName();
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * 获取服务器端口，如8080
     * @return String
     */
    public static String getServerPort() {
        try {
            return getRequest().getServerPort()+"";
        } catch (Exception e) {
            return ServerPort;
        }
    }
    /**
     * 获用户地址，如：127.0.0.1
     * @return String
     */
    public static String getRemoteAddr() {
        try {
            String remoteAddr = getRequest().getHeader("X-Real-IP");
            if (StringUtils.isNotBlank(remoteAddr)) {
                remoteAddr = getRequest().getHeader("X-Forwarded-For");
            } else if (StringUtils.isNotBlank(remoteAddr)) {
                remoteAddr = getRequest().getHeader("Proxy-Client-IP");
            } else if (StringUtils.isNotBlank(remoteAddr)) {
                remoteAddr = getRequest().getHeader("WL-Proxy-Client-IP");
            }
            return remoteAddr != null ? remoteAddr : getRequest().getRemoteAddr();
        } catch (Exception e) {
            return "";
        }
    }
    /**
     * 获取项目的访问路径，如： localhost:8080/xx
     * @return String
     */
    public static String getObjUrl() {
        return getServerName()+":"+getServerPort()+getServletPath();
    }
}
