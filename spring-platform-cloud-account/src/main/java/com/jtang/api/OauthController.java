package com.jtang.api;

import com.jtang.account.service.ITbUserService;
import core.account.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 开放给Oauth的接口
 * @date 2020/6/13 18:10
 * @author LinJinTang
 */
@RestController
@RequestMapping("/api")
public class OauthController {

    @Autowired
    private ITbUserService iTbUserService;

    @GetMapping("/userDetail")
    public UserDetail getUserDetail(@RequestParam("username") String username){
        return iTbUserService.loadUserByName(username);
    }
}
