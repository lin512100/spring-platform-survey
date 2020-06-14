package com.jtang.account.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jtang.account.entity.TbRolePermission;
import com.jtang.account.mapper.TbRolePermissionMapper;
import com.jtang.account.service.ITbRolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* 角色权限中间表 服务实现类
* @author jtang
* @since 2020-06-13
*/
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class TbRolePermissionServiceImpl extends ServiceImpl<TbRolePermissionMapper, TbRolePermission> implements ITbRolePermissionService {

    @Override
    public List<Long> getPermissionByRoleId(List<Long> roleId) {
        if(roleId == null || roleId.size() == 0){
            return new ArrayList<>();
        }

        QueryWrapper<TbRolePermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(TbRolePermission::getRoleId, roleId);
        return list(queryWrapper).stream().map(TbRolePermission::getPermissionId).collect(Collectors.toList());
    }
}

