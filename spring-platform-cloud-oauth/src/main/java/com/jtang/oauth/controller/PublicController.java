package com.jtang.oauth.controller;

import com.jtang.base.client.PublicUrlConstants;
import com.jtang.common.model.account.request.LoginRequest;
import com.jtang.base.utils.ResultUtils;
import com.jtang.oauth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 公共接口
 * @date 2020/7/21 0:04
 * @author LinJinTang
 */
@RestController
@RequestMapping(PublicUrlConstants.PUBLIC_PRE)
public class PublicController {

    @Autowired
    private AuthService authService;

    /**
     * 用户登录
     * @param loginRequest {@link LoginRequest}
     * @return {@link LoginRequest}
     * */
    @PostMapping(PublicUrlConstants.LOGIN)
    public ResultUtils login(LoginRequest loginRequest) {
        return ResultUtils.build(authService.getToken(loginRequest));
    }

    /** 获取JWT令牌 */
    @GetMapping(PublicUrlConstants.JWT)
    public ResultUtils jwt() {
        String jwtToken = authService.jwtToken();
        if(jwtToken == null){
            return ResultUtils.fail;
        }
        return ResultUtils.build(jwtToken);
    }
}
