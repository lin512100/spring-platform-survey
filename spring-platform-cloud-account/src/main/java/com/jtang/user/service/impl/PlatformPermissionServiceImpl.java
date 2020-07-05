package com.jtang.user.service.impl;

import com.jtang.common.model.account.entity.PlatformPermission;
import com.jtang.user.mapper.PlatformPermissionMapper;
import com.jtang.user.service.IPlatformPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* <p>
* 权限 服务实现类
* </p>
*
* @author jtang
* @since 2020-06-30
*/
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class PlatformPermissionServiceImpl extends ServiceImpl<PlatformPermissionMapper, PlatformPermission> implements IPlatformPermissionService {


}

