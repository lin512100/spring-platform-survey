package com.jtang.system.service.impl;

import com.jtang.system.entity.SysUserRole;
import com.jtang.system.mapper.SysUserRoleMapper;
import com.jtang.system.service.ISysUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
* 用户角色 服务实现类
* @author jtang
* @since 2020-09-06
*/
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {

}

