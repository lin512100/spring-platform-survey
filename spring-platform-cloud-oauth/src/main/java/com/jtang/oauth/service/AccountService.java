package com.jtang.oauth.service;

import com.jtang.oauth.service.impl.AccountServiceFallBackImpl;
import core.account.UserInfo;
import core.constants.ServiceConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户服务
 * @date 2020/6/13 23:03
 * @author LinJinTang
 */
@FeignClient(name= ServiceConstants.ACCOUNT_SERVICE, fallback = AccountServiceFallBackImpl.class)
public interface AccountService {

    /**
     * 获取用户信息
     * @param username 用户名
     * @return {@link UserInfo}
     * */
    @GetMapping(value = "/api/userDetail")
    UserInfo getUserDetail(@RequestParam(value = "username") String username);
}
