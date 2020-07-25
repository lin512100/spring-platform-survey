package com.jtang.oauth.controller;

import com.jtang.base.utils.ResultUtils;
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

    /** 退出登录 */
    @PostMapping("/logout")
    public ResultUtils logout() {
        authService.logout();
        return ResultUtils.success;
    }
}
