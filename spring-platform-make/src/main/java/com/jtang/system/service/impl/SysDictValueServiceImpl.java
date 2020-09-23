package com.jtang.system.service.impl;

import com.jtang.system.entity.SysDictValue;
import com.jtang.system.mapper.SysDictValueMapper;
import com.jtang.system.service.ISysDictValueService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
/**
* 字典值 服务实现类
* @author jtang
* @since 2020-09-13
*/
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class SysDictValueServiceImpl extends ServiceImpl<SysDictValueMapper, SysDictValue> implements ISysDictValueService {

}

