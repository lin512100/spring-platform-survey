package com.jtang.base.utils;

import java.util.UUID;

/**
 * UID工具类
 * @date 2020/7/25 16:02
 * @author LinJinTang
 */
public class UIDUtils {

    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

}
