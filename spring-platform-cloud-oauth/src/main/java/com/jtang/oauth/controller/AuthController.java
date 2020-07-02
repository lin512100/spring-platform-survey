package com.jtang.oauth.controller;

import com.jtang.common.model.base.response.CommonCode;
import com.jtang.common.model.base.response.ResponseResult;
import com.jtang.common.model.oauth.request.LoginRequest;
import com.jtang.common.model.oauth.response.LoginResult;
import com.jtang.oauth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @date 2020/7/2 21:33
 * @author LinJinTang
 */
@RestController
@RequestMapping("/")
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * 用户登录
     * @param loginRequest {@link LoginRequest}
     * */
    @PostMapping("/login")
    public LoginResult login(LoginRequest loginRequest) {
        return new LoginResult(CommonCode.SUCCESS,authService.login(loginRequest));
    }

    /**
     * 用户退出
     * */
    public ResponseResult logout() {
        return null;
    }
}
