package com.jtang.service;

import com.jtang.service.impl.AccountServiceFallBackImpl;
import core.account.UserDetail;
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
     * @return {@link UserDetail}
     * */
    @GetMapping(value = "/api/userDetail")
    UserDetail getUserDetail(@RequestParam(value = "username") String username);
}
