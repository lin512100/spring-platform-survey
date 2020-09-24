package com.jtang.account.service.impl;

import com.jtang.account.entity.TbAccount;
import com.jtang.account.mapper.TbAccountMapper;
import com.jtang.account.service.ITbAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
/**
*  服务实现类
* @author jtang
* @since 2020-09-24
*/
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class TbAccountServiceImpl extends ServiceImpl<TbAccountMapper, TbAccount> implements ITbAccountService {

}

