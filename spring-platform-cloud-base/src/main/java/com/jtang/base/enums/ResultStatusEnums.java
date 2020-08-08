package com.jtang.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * description 统一返回编码
 * @date 2020/2/28 15:19
 * @author LinJinTang
 */
@Getter
@AllArgsConstructor
public enum ResultStatusEnums {
    /**
     * 调用成功
     */
    SUCCESS(1, "成功"),

    /**
     * 调用失败
     */
    FAIL(0, "失败");


    /**
     * 返回编码
     */
    private Integer code;

    /**
     * 编码对应的消息
     */
    private String msg;

    /**
     * 根据枚举名称获取枚举编码
     */
    public static Integer getCode(String name) {
        for (ResultStatusEnums resultCode : ResultStatusEnums.values()) {
            if (resultCode.name().equals(name)) {
                return resultCode.code;
            }
        }
        return null;
    }
}
