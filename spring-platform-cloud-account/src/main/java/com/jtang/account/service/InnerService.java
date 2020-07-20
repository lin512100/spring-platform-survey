package com.jtang.account.service;

import com.jtang.common.model.account.response.HandleAllow;

import java.util.List;
import java.util.Map;

/**
 * 内部接口服务类
 * @author linjt
 * @date 2020/7/20
 */
public interface InnerService {

    Map<String,List<HandleAllow>> getHandleAllow();
}
