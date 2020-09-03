package com.jtang.make.service.impl;

import com.jtang.make.entity.TbResourceInfo;
import com.jtang.make.mapper.TbResourceInfoMapper;
import com.jtang.make.service.ITbResourceInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
/**
* 资源信息 服务实现类
* @author jtang
* @since 2020-09-03
*/
@Service
@Transactional
@Slf4j
public class TbResourceInfoServiceImpl extends ServiceImpl<TbResourceInfoMapper, TbResourceInfo> implements ITbResourceInfoService {

}

