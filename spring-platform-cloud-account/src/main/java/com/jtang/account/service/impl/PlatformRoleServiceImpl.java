package com.jtang.account.service.impl;

import com.jtang.account.service.IPlatformPermissionService;
import com.jtang.common.model.account.entity.PlatformRole;
import com.jtang.account.mapper.PlatformRoleMapper;
import com.jtang.account.service.IPlatformRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jtang.common.model.account.response.MenuTree;
import com.jtang.common.utils.TreeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
* <p>
* 角色 服务实现类
* </p>
*
* @author jtang
* @since 2020-06-30
*/
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class PlatformRoleServiceImpl extends ServiceImpl<PlatformRoleMapper, PlatformRole> implements IPlatformRoleService {
    @Autowired
    private IPlatformPermissionService iPlatformPermissionService;

    @Override
    public List<MenuTree> getMenuTree() {
        // 菜单列表
        List<MenuTree> menuTrees = this.baseMapper.menuTree();
        // 权限列表
        List<MenuTree> roleMenuTree = iPlatformPermissionService.menuPermissionList().stream().peek(menuTree -> {
                    if (menuTree.getChoice() == null) {
                        menuTree.setChoice(0);
                    } else {
                        menuTree.setChoice(1);
                    }
                }
        ).collect(Collectors.toList());
        menuTrees.addAll(roleMenuTree);
        try{
            List<MenuTree> menuTrees1 = TreeUtils.buildByRecursive(menuTrees, "Id",  "Pid", "Child");
        }catch (Exception e){
            e.printStackTrace();;
        }
        return null;
    }
}

