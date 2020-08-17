package com.jtang.base.utils;


import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.jtang.base.enums.ResultStatusEnums;
import com.jtang.base.response.ResultCode;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 统一返回类
 * @date 2020/7/5 14:46
 * @author LinJinTang
 */
@Getter
public class ResultUtils<T> implements Serializable {
    private static final long serialVersionUID = 3436477890959388499L;

    /**
     * 返回操作码（默认为正常）
     */
    private int code = ResultStatusEnums.SUCCESS.getCode();

    /**
     * 返回数据信息
     */
    private T data;

    /**
     * 返回正确消息信息
     */
    private String msg = "";


    public static ResultUtils success = new ResultUtils();
    public static ResultUtils fail = new ResultUtils().code(ResultStatusEnums.FAIL.getCode());

    public static ResultUtils build() {
        return new ResultUtils();
    }

    public static <T> ResultUtils build(T data) {
        return new ResultUtils().data(data);
    }

    private ResultUtils data(T data) {
        this.data = data;
        return this;
    }

    public ResultUtils code(Integer code) {
        this.code = code;
        return this;
    }

    public ResultUtils msg(String msg) {
        this.msg = msg;
        return this;
    }

    public static ResultUtils<Object> errorMsg(String msg){
        ResultUtils<Object> resultUtils = new ResultUtils<>();
        resultUtils.code = ResultStatusEnums.FAIL.getCode();
        resultUtils.msg = msg;
        return resultUtils;

    }

    public ResultUtils error(String msg) {
        this.msg = msg;
        this.code = ResultStatusEnums.FAIL.getCode();
        return this;
    }

    public static <T> ResultUtils error(ResultCode resultCode){
        return new ResultUtils().error(resultCode.message());
    }

    @Override
    public String toString() {
        JSONObject result = new JSONObject();
        result.put("code",code);
        if(StringUtils.isNotBlank(msg)){
            result.put("msg",msg);
        }
        if(data != null){
            result.put("data",data);
        }
        return result.toString();
    }

    public static ResultUtils<Object> getData(String data){
        JSONObject jsonObject = JSONObject.parseObject(data);
        ResultUtils<Object> resultUtils = new ResultUtils<>();
        if(jsonObject.containsKey("code")){
            resultUtils.code(Integer.parseInt(jsonObject.get("code").toString()));
        }
        if(jsonObject.containsKey("msg")){
            resultUtils.msg(jsonObject.get("msg").toString());
        }
        if(jsonObject.containsKey("data")){
            resultUtils.msg(jsonObject.get("data").toString());
        }
        return resultUtils;

    }
}
