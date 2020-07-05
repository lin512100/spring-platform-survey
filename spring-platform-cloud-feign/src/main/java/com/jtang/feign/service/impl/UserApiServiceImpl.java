package com.jtang.feign.service.impl;

import com.jtang.base.exception.ExceptionCast;
import com.jtang.common.model.account.response.AuthCode;
import com.jtang.feign.model.UserDao;
import com.jtang.feign.service.UserApiService;
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
        ExceptionCast.cast(AuthCode.AUTH_CREDENTIAL_ERROR);
        return null;
    }
}
