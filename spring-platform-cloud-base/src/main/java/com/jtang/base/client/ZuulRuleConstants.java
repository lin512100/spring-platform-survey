package com.jtang.base.client;

/**
 * 路由匹配规则
 * @author linjt
 * @date 2020/7/20
 */
public class ZuulRuleConstants {

    /** 默认包含public字段的链接全部放行 */
    public static final String DEFAULT_PASS = "public";

    /** 默认包含inner字段的链接全部为内部调用，禁止外部访问 */
    public static final String DEFAULT_REFUSE = "inner";
}
