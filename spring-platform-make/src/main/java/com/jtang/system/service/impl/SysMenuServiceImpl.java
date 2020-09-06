package com.jtang.system.service.impl;

import com.jtang.base.utils.TreeUtils;
import com.jtang.common.model.account.response.PlatformMenuDTO;
import com.jtang.system.entity.SysMenu;
import com.jtang.system.mapper.SysMenuMapper;
import com.jtang.system.service.ISysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
* 菜单表 服务实现类
* @author jtang
* @since 2020-09-06
*/
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    @Override
    public List<PlatformMenuDTO> getTreeById(Long userId) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        List<PlatformMenuDTO> menuList = this.baseMapper.getMenuTree(userId);
        return TreeUtils.buildByRecursive(menuList,"Id","Pid","Children");
    }

}

