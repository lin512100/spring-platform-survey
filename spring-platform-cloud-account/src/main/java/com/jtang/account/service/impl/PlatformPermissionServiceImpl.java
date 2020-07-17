package com.jtang.account.service.impl;

import com.jtang.account.query.PlatformPermissionQueryDTO;
import com.jtang.base.utils.Pagination;
import com.jtang.common.model.account.entity.PlatformPermission;
import com.jtang.account.mapper.PlatformPermissionMapper;
import com.jtang.account.service.IPlatformPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jtang.common.model.account.response.PlatformMenuDTO;
import com.jtang.common.model.account.response.PlatformPermissionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
* 权限 服务实现类
* @author jtang
* @since 2020-06-30
*/
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class PlatformPermissionServiceImpl extends ServiceImpl<PlatformPermissionMapper, PlatformPermission> implements IPlatformPermissionService {


    @Override
    public Pagination<PlatformPermissionDTO> list(PlatformPermissionQueryDTO queryDTO) {

        List<PlatformPermissionDTO> permissionList = this.baseMapper.list(queryDTO);
        queryDTO.setPageIndex(null);
        queryDTO.setPageSize(null);
        List<PlatformPermissionDTO> total = this.baseMapper.list(queryDTO);
        return new Pagination<PlatformPermissionDTO>((total == null)?0:total.size(),(permissionList == null)? new ArrayList<>():permissionList);
    }

    @Override
    public List<PlatformMenuDTO> menuPermissionList() {
        return baseMapper.menuPermissionList();
    }
}

