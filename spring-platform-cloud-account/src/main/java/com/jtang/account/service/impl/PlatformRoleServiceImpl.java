package com.jtang.account.service.impl;

import com.jtang.common.model.account.entity.PlatformRole;
import com.jtang.account.mapper.PlatformRoleMapper;
import com.jtang.account.service.IPlatformRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*** <p>
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

