package com.jtang.zuul.service;

import java.util.List;
import java.util.Map;

/**
 * 用户操作权限服务类
 * @author linjt
 * @date 2020/7/20
 */
public interface RoleService {

    /**
     * 获取角色的操作权限
     * */
    List<Map<String,Object>> getRole();
}
