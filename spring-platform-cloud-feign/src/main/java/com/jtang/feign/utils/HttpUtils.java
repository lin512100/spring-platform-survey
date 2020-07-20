package com.jtang.feign.utils;

import org.springframework.util.Base64Utils;

/**
 * HTTP工具类
 * @date 2020/7/4 15:05
 * @author LinJinTang
 */
public class HttpUtils {

    /** 获取http basic的串 */
    public static String getHttpBasic(String clientId,String clientSecret){
        String string = clientId + ":" + clientSecret;
        //将串进行base64编码
        byte[] encode = Base64Utils.encode(string.getBytes());
        return "Basic "+ new String(encode);
    }


}
