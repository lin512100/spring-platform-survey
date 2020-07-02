package com.jtang.oauth.service.impl;

import com.jtang.common.model.oauth.entity.PlatformMenu;
import com.jtang.oauth.mapper.PlatformMenuMapper;
import com.jtang.oauth.service.IPlatformMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* <p>
* 菜单表 服务实现类
* </p>
*
* @author jtang
* @since 2020-06-30
*/
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class PlatformMenuServiceImpl extends ServiceImpl<PlatformMenuMapper, PlatformMenu> implements IPlatformMenuService {

    @Override
    public List<PlatformMenu> getMenuByUserId(long userId) {
        return this.baseMapper.getMenuByUserId(userId);
    }
}

