package com.jtang.feign.service;

import com.jtang.base.client.InnerUrlConstants;
import com.jtang.base.client.ServiceConstants;
import com.jtang.common.model.account.response.HandleAllow;
import com.jtang.feign.InnerFeignRequest;
import com.jtang.feign.model.UserDao;
import com.jtang.feign.service.impl.UserApiServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

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
    @GetMapping(InnerUrlConstants.INNER_PRE + InnerUrlConstants.USER_INFO)
    UserDao getUsernameInfo(@RequestParam("username") String username);

    /**
     * 根据角色ID查询用户操作权限
     * @param roleIds 角色ID
     * @return {@link HandleAllow}
     * */
    @GetMapping(InnerUrlConstants.INNER_PRE + InnerUrlConstants.ALLOW_HANDLE)
    Map<String, List<HandleAllow>> getHandleAllow(@RequestParam("roleIds") String roleIds);
}
