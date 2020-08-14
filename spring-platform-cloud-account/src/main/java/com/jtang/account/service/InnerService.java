package com.jtang.account.service;

import com.jtang.common.model.account.response.HandleAllow;

import java.util.List;
import java.util.Map;

/**
 * 内部接口服务类
 * @author lin512100
 * @date 2020/7/20
 */
public interface InnerService {

    /**
     * 根据角色ID查询操作列表
     * @param roleIds 角色ID
     * @return List<HandleAllow>
     * */
    Map<String,List<HandleAllow>> getHandleAllow(List<Long> roleIds);
}
