package com.jtang.resource.service.impl;

import com.jtang.resource.entity.TbResourceVisitLog;
import com.jtang.resource.mapper.TbResourceVisitLogMapper;
import com.jtang.resource.service.ITbResourceVisitLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
/**
*  服务实现类
* @author jtang
* @since 2020-09-13
*/
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class TbResourceVisitLogServiceImpl extends ServiceImpl<TbResourceVisitLogMapper, TbResourceVisitLog> implements ITbResourceVisitLogService {

}

