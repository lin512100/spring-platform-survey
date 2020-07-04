package com.jtang.oauth.service.api.impl;

import com.jtang.feign.model.UserDao;
import com.jtang.oauth.service.api.UserApiService;
import org.springframework.stereotype.Service;

/**
 * 用户服务降级
 * @date 2020/7/4 16:27
 * @author LinJinTang
 */
@Service
public class UserApiServiceImpl implements UserApiService {

    @Override
    public UserDao getUsernameInfo(String username) {
        throw new RuntimeException("用户服务接口调用失败");
    }
}
