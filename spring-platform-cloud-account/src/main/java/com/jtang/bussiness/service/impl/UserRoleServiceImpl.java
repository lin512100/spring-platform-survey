package com.jtang.bussiness.service.impl;

import com.jtang.bussiness.entity.UserRole;
import com.jtang.bussiness.mapper.UserRoleMapper;
import com.jtang.bussiness.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
/**
* <p>
* 角色用户 服务实现类
* </p>
*
* @author jtang
* @since 2020-06-02
*/
@Service
@Transactional
@Slf4j
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}

