package com.jtang.web.base;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * description 统一返回编码
 * @date 2020/2/28 15:19
 * @author LinJinTang
 */
@Getter
@AllArgsConstructor
public enum ResultCodeEnum {
    /**
     * 调用成功
     */
    SUCCESS(0, "成功"),

    /**
     * 调用失败
     */
    FAIL(-1, "失败");


    /**
     * 返回编码
     */
    private Integer code;

    /**
     * 编码对应的消息
     */
    private String msg;

    /**
     * 根据枚举名称--获取枚举编码
     */
    public static Integer getCode(String name) {
        for (ResultCodeEnum resultCode : ResultCodeEnum.values()) {
            if (resultCode.name().equals(name)) {
                return resultCode.code;
            }
        }
        return null;
    }
}
