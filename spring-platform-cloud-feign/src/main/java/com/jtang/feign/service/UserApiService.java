package com.jtang.feign.service;

import com.jtang.base.client.ServiceConstants;
import com.jtang.feign.InnerFeignRequest;
import com.jtang.feign.model.UserDao;
import com.jtang.feign.service.impl.UserApiServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户服务接口类
 * @date 2020/7/4 16:13
 * @author LinJinTang
 */
@FeignClient(value = ServiceConstants.USER_SERVICE, configuration = InnerFeignRequest.class,fallback = UserApiServiceImpl.class)
public interface UserApiService {

    /**
     * 根据账号查询用户信息
     * @param username 用户名
     * @return {@link UserDao}
     * */
    @GetMapping("/user/info")
    UserDao getUsernameInfo(@RequestParam("username") String username);
}
