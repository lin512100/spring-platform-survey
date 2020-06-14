package com.jtang.account.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jtang.account.entity.TbPermission;
import com.jtang.account.entity.TbUser;
import com.jtang.account.mapper.TbUserMapper;
import com.jtang.account.service.ITbPermissionService;
import com.jtang.account.service.ITbRolePermissionService;
import com.jtang.account.service.ITbUserRoleService;
import com.jtang.account.service.ITbUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import core.account.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
* <p>
* 用户 服务实现类
* </p>
*
* @author jtang
* @since 2020-06-13
*/
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper, TbUser> implements ITbUserService {

    @Autowired
    private ITbUserRoleService iTbUserRoleService;

    @Autowired
    private ITbRolePermissionService iTbRolePermissionService;

    @Autowired
    private ITbPermissionService iTbPermissionService;

    @Override
    public UserInfo loadUserByName(String username) {
        QueryWrapper<TbUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(TbUser::getUsername,username);
        // 查询用户信息
        TbUser user = getOne(queryWrapper);
        if(user == null){
            return new UserInfo();
        }
        // 查询角色ID
        List<Long> roleIds = iTbUserRoleService.getRoleIdByUserId(user.getId());

        // 查询权限ID
        List<Long> permissionIds = iTbRolePermissionService.getPermissionByRoleId(roleIds);

        // 查询权限信息
        List<TbPermission> permissions = iTbPermissionService.getListById(permissionIds);

        // 封装用户信息
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(user,userInfo);
        // 封装权限信息
        List<String> authorities = permissions.stream().map(TbPermission::getUrl).collect(Collectors.toList());
        userInfo.setRole(authorities);
        log.info("loadUserByName :" + username);
        return userInfo;
    }

}

