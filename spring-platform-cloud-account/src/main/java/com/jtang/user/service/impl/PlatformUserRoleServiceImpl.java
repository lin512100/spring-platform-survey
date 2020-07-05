package com.jtang.user.service.impl;

import com.jtang.common.model.account.entity.PlatformUserRole;
import com.jtang.user.mapper.PlatformUserRoleMapper;
import com.jtang.user.service.IPlatformUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* 用户角色 服务实现类
*
* @author jtang
* @since 2020-06-30
*/
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class PlatformUserRoleServiceImpl extends ServiceImpl<PlatformUserRoleMapper, PlatformUserRole> implements IPlatformUserRoleService {

}

