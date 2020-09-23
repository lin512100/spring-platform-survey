package com.jtang.base.response;


/**
 * @date 2020/7/4 23:43
 * @author LinJinTang
 */
public interface ResultCode {

    /** 操作是否成功,true为成功,false操作失败 */
    boolean success();

    /** 操作代码 */
    int code();

    /** 提示信息 */
    String message();

}
