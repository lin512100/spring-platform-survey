package com.jtang.account.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jtang.account.entity.TbUser;
import com.jtang.account.entity.TbUserRole;
import com.jtang.account.mapper.TbUserRoleMapper;
import com.jtang.account.service.ITbUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.stream.Collectors;

/**
* <p>
* 用户角色 服务实现类
* </p>
*
* @author jtang
* @since 2020-06-13
*/
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class TbUserRoleServiceImpl extends ServiceImpl<TbUserRoleMapper, TbUserRole> implements ITbUserRoleService {

    @Override
    public List<Long> getRoleIdByUserId(long userId) {
        QueryWrapper<TbUserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(TbUserRole::getUserId,userId);
        return list(queryWrapper).stream().map(TbUserRole::getRoleId).collect(Collectors.toList());
    }

}

