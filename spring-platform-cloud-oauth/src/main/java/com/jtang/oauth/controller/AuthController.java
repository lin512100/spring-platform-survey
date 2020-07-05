package com.jtang.oauth.controller;

import com.jtang.common.model.account.request.LoginRequest;
import com.jtang.common.utils.ResultUtils;
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
    public ResultUtils login(LoginRequest loginRequest) {
        return ResultUtils.build(authService.getToken(loginRequest));
    }

    /** 获取JWT令牌 */
    @GetMapping("/jwt")
    public ResultUtils jwt() {
        String jwtToken = authService.jwtToken();
        if(jwtToken == null){
            return ResultUtils.fail;
        }
        return ResultUtils.build(jwtToken);
    }

    /** 退出登录 */
    @PostMapping("/logout")
    public ResultUtils logout() {
        authService.logout();
        return ResultUtils.success;
    }
}
