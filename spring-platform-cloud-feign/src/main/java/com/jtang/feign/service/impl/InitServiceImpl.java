package com.jtang.feign.service.impl;

import com.jtang.base.exception.ExceptionCast;
import com.jtang.common.model.account.response.AuthCode;
import com.jtang.common.utils.ResultUtils;
import com.jtang.feign.service.InitService;

import java.util.HashMap;
import java.util.List;

/**
 * @author linjt
 * @date 2020/7/22
 */
public class InitServiceImpl implements InitService {

    @Override
    public ResultUtils asyncOperateUrl(List<HashMap<String, String>> mapList) {
        ExceptionCast.cast(AuthCode.AUTH_CREDENTIAL_ERROR);
        return null;
    }
}
