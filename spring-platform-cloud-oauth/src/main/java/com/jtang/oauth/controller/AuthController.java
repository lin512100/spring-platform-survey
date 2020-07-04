package com.jtang.oauth.controller;

import com.jtang.common.model.base.response.CommonCode;
import com.jtang.common.model.base.response.ResponseResult;
import com.jtang.common.model.oauth.request.LoginRequest;
import com.jtang.common.model.oauth.response.JwtResult;
import com.jtang.common.model.oauth.response.LoginResult;
import com.jtang.oauth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
     * @return {@link LoginRequest}
     * */
    @PostMapping("/login")
    public LoginResult login(LoginRequest loginRequest) {
        return new LoginResult(CommonCode.SUCCESS,authService.getToken(loginRequest));
    }

    /** 获取JWT令牌 */
    @GetMapping("/jwt")
    public JwtResult userjwt() {
        String jwtToken = authService.jwtToken();
        if(jwtToken == null){
            return new JwtResult(CommonCode.FAIL,null);
        }
        return new JwtResult(CommonCode.SUCCESS,jwtToken);
    }

    /** 退出登录 */
    @PostMapping("/logout")
    public ResponseResult logout() {
        authService.logout();
        return new ResponseResult(CommonCode.SUCCESS);
    }
}
