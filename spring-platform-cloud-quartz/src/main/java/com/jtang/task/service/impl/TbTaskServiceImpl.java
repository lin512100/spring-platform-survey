package com.jtang.task.service.impl;

import com.jtang.task.entity.TbTask;
import com.jtang.task.mapper.TbTaskMapper;
import com.jtang.task.service.ITbTaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
/**
* 定时任务 服务实现类
* @author jtang
* @since 2020-09-25
*/
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class TbTaskServiceImpl extends ServiceImpl<TbTaskMapper, TbTask> implements ITbTaskService {

}

