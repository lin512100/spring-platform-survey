package com.jtang.base.exception;

import com.jtang.base.response.ResultCode;

/**
 * 异常处理类
 * @date 2020/7/2 21:02
 * @author LinJinTang
 */
public class ExceptionCast {

    public static void cast(ResultCode resultCode){
        throw new CustomException(resultCode);
    }
}
