package com.jtang.account.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jtang.account.query.PlatformRoleMenuDTO;
import com.jtang.common.model.account.entity.PlatformRoleMenu;
import com.jtang.account.mapper.PlatformRoleMenuMapper;
import com.jtang.account.service.IPlatformRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
*  服务实现类
* @author jtang
* @since 2020-07-11
*/
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class PlatformRoleMenuServiceImpl extends ServiceImpl<PlatformRoleMenuMapper, PlatformRoleMenu> implements IPlatformRoleMenuService {

    @Override
    public List<Long> getMenuIdByRoleId(List<Long> roleIds) {
        if(roleIds == null || roleIds.size() == 0){
            return new ArrayList<>();
        }
        return this.baseMapper.getMenuIdByRoleId(roleIds);
    }

    @Override
    public void modifyRoleMenu(PlatformRoleMenuDTO entity) {

        // 查询该角色已经有的权限
        List<Long> oldRole = this.getMenuIdByRoleId(Collections.singletonList(entity.getRoleId()));
        List<Long> menuIdByRoleId = new ArrayList<>(oldRole);
        // 删除的菜单ID
        menuIdByRoleId.removeAll(entity.getMenuId());
        // 新增的菜单ID
        entity.getMenuId().removeAll(oldRole);
        List<PlatformRoleMenu> list = new ArrayList<>();
        for(Long id : entity.getMenuId()){
            System.out.println("新增：" + entity.getMenuId());
            list.add(new PlatformRoleMenu(entity.getRoleId(),id));
        }
        saveBatch(list);
        if(menuIdByRoleId.size() > 0){
            QueryWrapper<PlatformRoleMenu> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("role_id", entity.getRoleId());
            queryWrapper.in("menu_id",menuIdByRoleId);
            System.out.println("删除：" + entity.getMenuId());
            this.baseMapper.delete(queryWrapper);
        }

    }
}

