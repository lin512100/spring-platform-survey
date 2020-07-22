package com.jtang.base.utils;

import java.io.UnsupportedEncodingException;

/**
 * 请求路径编码解码
 * @author linjt
 * @date 2020/7/22
 */
public class UrlUtils {

    private final static String ENCODE = "UTF-8";

    /** URL 解码 */
    public static String getURLDecoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLDecoder.decode(str, ENCODE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /** URL 转码 */
    public static String getURLEncoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLEncoder.encode(str, ENCODE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

}
