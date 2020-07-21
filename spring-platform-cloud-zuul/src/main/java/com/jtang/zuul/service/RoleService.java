package com.jtang.zuul.service;

import com.jtang.common.model.account.response.HandleAllow;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 用户操作权限服务类
 * @author linjt
 * @date 2020/7/20
 */
public interface RoleService {

    /**
     * 获取角色操作权限
     * @param roleIds  角色ID字符串
     * @param server 服务器
     * @return {@link HandleAllow}
     * */
    Set<HandleAllow> getRoleItem(String roleIds, String server);

    /**
     * 读取单个角色操作URL
     * @param roleId 角色ID
     * @return Map<String, List<HandleAllow>>
     * */
    Map<String, List<HandleAllow>> readSingleRoleOperate(String roleId);
}
