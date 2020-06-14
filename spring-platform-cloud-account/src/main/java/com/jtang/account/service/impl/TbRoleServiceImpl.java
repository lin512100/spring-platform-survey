package com.jtang.account.service.impl;

import com.jtang.account.entity.TbRole;
import com.jtang.account.mapper.TbRoleMapper;
import com.jtang.account.service.ITbRoleService;
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
* @since 2020-06-13
*/
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class TbRoleServiceImpl extends ServiceImpl<TbRoleMapper, TbRole> implements ITbRoleService {

}

