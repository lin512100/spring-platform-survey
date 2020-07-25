package com.jtang.zuul.service.impl;

import com.jtang.common.model.account.response.HandleAllow;
import com.jtang.feign.service.UserApiService;
import com.jtang.zuul.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 实现类
 * @date 2020/7/21 21:38
 * @author LinJinTang
 */
@Service
@CacheConfig(cacheNames = "ROLE_SERVER_OPERATE")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private UserApiService userApiService;

    /** 解决缓存不能调用自己内部方法的问题 */
    @Resource
    private RoleService self;

    @Override
    public Set<HandleAllow> getRoleItem(String roleIds, String server) {
        String[] roleIdStr = roleIds.split(",");
        if(roleIdStr.length == 0){
            return new HashSet<>();
        }
        // 操作权限集合
        Set<HandleAllow> data = new HashSet<>();

        for(String role :roleIdStr){
            Map<String, List<HandleAllow>> stringListMap = self.readSingleRoleOperate(role);
            if(stringListMap.containsKey(server)){
                data.addAll(stringListMap.get(server));
            }
        }
        return data;
    }

    @Override
    @Cacheable(value = "ROLE_URL", key = "#roleId")
    public Map<String, List<HandleAllow>> readSingleRoleOperate(String roleId) {
        return userApiService.getHandleAllow(roleId);
    }



}
