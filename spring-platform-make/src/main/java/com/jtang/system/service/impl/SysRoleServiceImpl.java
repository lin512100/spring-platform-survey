package com.jtang.system.service.impl;

import com.jtang.system.entity.SysRole;
import com.jtang.system.mapper.SysRoleMapper;
import com.jtang.system.service.ISysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
* 角色 服务实现类
* @author jtang
* @since 2020-09-06
*/
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

}

