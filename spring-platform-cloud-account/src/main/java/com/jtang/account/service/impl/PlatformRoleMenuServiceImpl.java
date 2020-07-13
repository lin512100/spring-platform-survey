package com.jtang.account.service.impl;

import com.jtang.account.query.PlatformMenuQueryDTO;
import com.jtang.base.utils.Pagination;
import com.jtang.common.model.account.entity.PlatformRoleMenu;
import com.jtang.account.mapper.PlatformRoleMenuMapper;
import com.jtang.account.service.IPlatformRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jtang.common.model.account.response.PlatformMenuDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* <p>
*  服务实现类
* </p>
*
* @author jtang
* @since 2020-07-11
*/
@Service
@Transactional
@Slf4j
public class PlatformRoleMenuServiceImpl extends ServiceImpl<PlatformRoleMenuMapper, PlatformRoleMenu> implements IPlatformRoleMenuService {


}

