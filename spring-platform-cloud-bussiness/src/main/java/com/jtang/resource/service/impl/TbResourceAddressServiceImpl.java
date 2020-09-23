package com.jtang.resource.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jtang.resource.entity.TbResourceAddress;
import com.jtang.resource.mapper.TbResourceAddressMapper;
import com.jtang.resource.service.ITbResourceAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.management.Query;
import java.util.ArrayList;
import java.util.List;

/**
* 资源地址 服务实现类
* @author jtang
* @since 2020-09-07
*/
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class TbResourceAddressServiceImpl extends ServiceImpl<TbResourceAddressMapper, TbResourceAddress> implements ITbResourceAddressService {

    @Override
    public List<TbResourceAddress> getResourceAddress(String uuid, Integer channel, Integer belong) {
        QueryWrapper<TbResourceAddress> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uuid",uuid).eq("channel",channel).eq("belong", belong);
        List<TbResourceAddress> list = list(queryWrapper);
        return (list == null)?new ArrayList<>():list;
    }
}

