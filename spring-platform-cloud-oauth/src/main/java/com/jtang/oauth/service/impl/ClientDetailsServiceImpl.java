package com.jtang.oauth.service.impl;

import com.jtang.common.model.account.entity.OauthClientDetails;
import com.jtang.oauth.mapper.ClientDetailsMapper;
import com.jtang.oauth.service.IClientDetailsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* 客户端信息 服务实现类
* @author jtang
* @since 2020-07-01
*/
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class ClientDetailsServiceImpl extends ServiceImpl<ClientDetailsMapper, OauthClientDetails> implements IClientDetailsService {

}

