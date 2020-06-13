package com.jtang.service.impl;

import com.jtang.service.AccountService;
import core.account.UserDetail;
import core.constants.ServiceConstants;
import lombok.extern.slf4j.Slf4j;

/**
 * 失败策略
 * @date 2020/6/13 23:07
 * @author LinJinTang
 */
@Slf4j
public class AccountServiceFallBackImpl implements AccountService {

    @Override
    public UserDetail getUserDetail(String username) {
        log.error(ServiceConstants.OAUTH_SERVICE + " call " + ServiceConstants.ACCOUNT_SERVICE + " error: " + username);
        return new UserDetail();
    }
}
