package com.jtang.make.service.impl;

import com.jtang.make.entity.TbResourceAddress;
import com.jtang.make.mapper.TbResourceAddressMapper;
import com.jtang.make.service.ITbResourceAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
/**
* 资源地址 服务实现类
* @author jtang
* @since 2020-09-03
*/
@Service
@Transactional
@Slf4j
public class TbResourceAddressServiceImpl extends ServiceImpl<TbResourceAddressMapper, TbResourceAddress> implements ITbResourceAddressService {

}

