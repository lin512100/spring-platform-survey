package com.jtang.system.service.impl;

import com.jtang.account.entity.TbAccount;
import com.jtang.account.service.ITbAccountService;
import com.jtang.system.entity.TbSystem;
import com.jtang.system.mapper.TbSystemMapper;
import com.jtang.system.service.ITbSystemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class TbSystemServiceImpl extends ServiceImpl<TbSystemMapper, TbSystem> implements ITbSystemService {

    @Autowired
    private ITbAccountService iTbAccountService;

    @Override
    public void all() {
        iTbAccountService.getBaseMapper().insert(new TbAccount(null,"all"));
        this.getBaseMapper().insert(new TbSystem(null,"system"));
        int i = 1/0;
    }

    @Override
    public void half() {

    }
}

