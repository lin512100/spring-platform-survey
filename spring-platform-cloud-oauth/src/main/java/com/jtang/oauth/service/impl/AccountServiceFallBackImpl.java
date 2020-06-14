package com.jtang.oauth.service.impl;

import com.jtang.oauth.service.AccountService;
import core.account.UserInfo;
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
    public UserInfo getUserDetail(String username) {
        log.error(ServiceConstants.OAUTH_SERVICE + " call " + ServiceConstants.ACCOUNT_SERVICE + " error: " + username);
        return null;
    }
}
