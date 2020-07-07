package com.jtang.base.enums;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author linjt
 * @date 2020/7/7
 */
@Getter
public enum UserStatusEnums {

    NORMAL(1,"正常"),
    FREEZE(2,"冻结"),
    CANCELLATION(3,"注销"),
    UNACTIVATED(4, "待激活");

    private int status;
    private String msg;

    UserStatusEnums(int status, String msg){
        this.status = status;
        this.msg = msg;
    }

    /**
     * 根据用户状态查询消息
     * */
    public static UserStatusEnums getMsg(int status){
        for (UserStatusEnums userStatusEnums :UserStatusEnums.values()) {
            if(userStatusEnums.status == status){
                return userStatusEnums;
            }
        }
        throw new RuntimeException("用户状态未定义");
    }

    /** 封装用户状态 */
    public static List<Map<String,Object>> getMap(){
        List<Map<String,Object>> data = new ArrayList<>();
        Map<String,Object> map;
        for (UserStatusEnums userStatusEnums :UserStatusEnums.values()) {
            map = new HashMap<>();
            map.put("status",userStatusEnums.getStatus());
            map.put("msg",userStatusEnums.getMsg());
            data.add(map);
        }
        return data;
    }
}
