package com.jtang.resource.service.impl;

import com.jtang.resource.entity.TbResourceTag;
import com.jtang.resource.mapper.TbResourceTagMapper;
import com.jtang.resource.service.ITbResourceTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
/**
* 资源标签 服务实现类
* @author jtang
* @since 2020-09-07
*/
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class TbResourceTagServiceImpl extends ServiceImpl<TbResourceTagMapper, TbResourceTag> implements ITbResourceTagService {

}

