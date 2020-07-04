package com.jtang.common.model.oauth.response;

import com.jtang.common.model.base.response.ResponseResult;
import com.jtang.common.model.base.response.ResultCode;
import lombok.*;

/**
 * @date 2020/7/4 11:34
 * @author LinJinTang
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class JwtResult extends ResponseResult {
    public JwtResult(ResultCode resultCode, String jwt) {
        super(resultCode);
        this.jwt = jwt;
    }
    private String jwt;
}
