package com.jtang.account.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jtang.account.query.PlatformMenuQueryDTO;
import com.jtang.base.utils.Pagination;
import com.jtang.common.model.account.entity.PlatformMenu;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jtang.account.mapper.PlatformMenuMapper;
import com.jtang.account.service.IPlatformMenuService;
import com.jtang.account.service.IPlatformUserRoleService;
import com.jtang.common.model.account.response.PlatformMenuDTO;
import com.jtang.common.model.account.response.PlatformUserDTO;
import com.jtang.common.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
* <p>
* 菜单表 服务实现类
* </p>
*
* @author jtang
* @since 2020-06-30
*/
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class PlatformMenuServiceImpl extends ServiceImpl<PlatformMenuMapper, PlatformMenu> implements IPlatformMenuService {

    @Autowired
    private IPlatformUserRoleService iPlatformUserRoleService;

    @Override
    public List<PlatformMenu> getMenuByUserId(long userId) {
        return this.baseMapper.getMenuByUserId(userId);
    }

    @Override
    @Cacheable(value = "getListByRole", key = "#userId")
    public List<PlatformMenu> getListByRole(long userId) {
        return this.baseMapper.getMenuByUserId(userId);
    }

    @Override
    public Pagination<PlatformMenuDTO> getMenuList(PlatformMenuQueryDTO queryDTO) {

        List<PlatformMenuDTO> userInfoList = this.baseMapper.getMenuList(queryDTO);
        queryDTO.setPageIndex(null);
        queryDTO.setPageSize(null);
        Long total = this.baseMapper.getMenuListCount(queryDTO);
        return new Pagination<PlatformMenuDTO>((total == null)?0:total,(userInfoList == null)? new ArrayList<>():userInfoList);
    }
}

