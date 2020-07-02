package com.jtang.common.model.oauth.response;

import com.jtang.common.model.base.response.ResponseResult;
import com.jtang.common.model.base.response.ResultCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 登录返回结果集
 * @date 2020/7/2 21:09
 * @author LinJinTang
 */
@Getter
@ToString
@NoArgsConstructor
public class LoginResult extends ResponseResult {
    public LoginResult(ResultCode resultCode, String token) {
        super(resultCode);
        this.token = token;
    }
    private String token;
}
