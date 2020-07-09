package com.jtang.base.enums;

import lombok.Getter;

/**
 * 性别枚举
 * @author linjt
 * @date 2020/7/9
 */
@Getter
public enum SexEnums {
    MALE(0,"男性"),
    FE_MALE(1,"女性"),
    UNDEFINE(2,"未知");

    private int status;
    private String msg;

    SexEnums(int status, String msg){
        this.status = status;
        this.msg = msg;
    }

    /**
     * 根据枚举状态查询消息
     * */
    public static SexEnums getMsg(int status){
        for (SexEnums sexEnums :SexEnums.values()) {
            if(sexEnums.status == status){
                return sexEnums;
            }
        }
        throw new RuntimeException("性别状态未定义");
    }

}
