package com.jtang.user.service.impl;

import com.jtang.common.model.oauth.entity.PlatformRole;
import com.jtang.user.mapper.PlatformRoleMapper;
import com.jtang.user.service.IPlatformRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}

