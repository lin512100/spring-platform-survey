package com.jtang.common.exception;

import lombok.Getter;
import lombok.Setter;


/**
 *
 * @date 2020/6/13 22:03
 * @author LinJinTang
 */
@Getter
@Setter
public class BusinessException extends RuntimeException {

    private Integer code;

    private String msg;

    public BusinessException(){
        this.code = -1;
    }

    public BusinessException(int code , String msg){
        this.code = code;
        this.msg = msg;
    }
    public BusinessException(String msg){
        this.code = 400;
        this.msg = msg;
    }

}
