package com.jtang.feign.service.impl;

import com.jtang.base.exception.ExceptionCast;
import com.jtang.common.model.account.response.AuthCode;
import com.jtang.common.model.account.response.HandleAllow;
import com.jtang.feign.model.UserDao;
import com.jtang.feign.service.UserApiService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    @Override
    public Map<String, List<HandleAllow>> getHandleAllow(String roleIds) {
        ExceptionCast.cast(AuthCode.AUTH_CREDENTIAL_ERROR);
        return null;
    }
}
