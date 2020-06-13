package core.account;

import lombok.Getter;

/**
 * 用户账户状态枚举
 * @date 2020/6/13 17:24
 * @author LinJinTang
 */
@Getter
public enum UserStatusEnums {

    /** 正常 */
    NORMAL(1,"正常");

    private int status;

    private String desc;

    UserStatusEnums(int status, String desc){
        this.status = status;
        this.desc = desc;
    }
}
