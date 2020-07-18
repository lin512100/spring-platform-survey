package com.jtang.base.utils;

import com.alibaba.fastjson.JSONObject;

/**
 * 权限方法操作工具类
 * @date 2020/7/18 13:15
 * @author LinJinTang
 */
public class OperateFunctionUtils {
    private final static String FUNCTION_URL = "url";
    private final static String FUNCTION_METHOD = "method";
    private final static String FUNCTION_OPERATE = "operate";


    /**
     * 处理菜单字符串列表工具类
     * @param url 操作链接
     * @param operate 操作名称
     * @param method 权限方法
     * @return String
     * */
    public static String getStr(String url, String method, String operate){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(FUNCTION_URL, url);
        jsonObject.put(FUNCTION_METHOD, method);
        jsonObject.put(FUNCTION_OPERATE, operate);
        return jsonObject.toJSONString();
    }
}
