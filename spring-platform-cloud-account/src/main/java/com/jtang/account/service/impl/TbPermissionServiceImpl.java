package com.jtang.account.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jtang.account.entity.TbPermission;
import com.jtang.account.mapper.TbPermissionMapper;
import com.jtang.account.service.ITbPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.ArrayList;
import java.util.List;

/**
* <p>
* 权限 服务实现类
* </p>
*
* @author jtang
* @since 2020-06-13
*/
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class TbPermissionServiceImpl extends ServiceImpl<TbPermissionMapper, TbPermission> implements ITbPermissionService {

    @Override
    public List<TbPermission> getListById(List<Long> permissionId) {
        if(permissionId == null || permissionId.size() == 0){
            return new ArrayList<>();
        }
        QueryWrapper<TbPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(TbPermission::getId,permissionId);
        return list(queryWrapper);
    }
}

