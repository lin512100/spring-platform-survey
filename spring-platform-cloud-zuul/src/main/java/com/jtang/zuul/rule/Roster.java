package com.jtang.zuul.rule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @date 2020/7/5 13:03
 * @author LinJinTang
 */
public class Roster {

    /** IP 黑名单 */
    public final static List<String> BLACK_IP = Collections.singletonList(
            "400.400.400.400"
    );

    /** 链接放行白名单 */
    public final static List<String> WHITE_ADDR = new ArrayList<>(Arrays.asList(
            "/auth/login",
            "/auth/jwt",

            "/account/info",
            "/account/menu/listById/**"
    ));
}

