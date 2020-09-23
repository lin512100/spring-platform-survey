package com.jtang.resource.service.impl;

import com.jtang.resource.entity.TbResourceAccount;
import com.jtang.resource.mapper.TbResourceAccountMapper;
import com.jtang.resource.service.ITbResourceAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
/**
* 资源账号 服务实现类
* @author jtang
* @since 2020-09-07
*/
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class TbResourceAccountServiceImpl extends ServiceImpl<TbResourceAccountMapper, TbResourceAccount> implements ITbResourceAccountService {

}

