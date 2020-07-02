package com.jtang.oauth.service;

import com.jtang.common.model.auth.PlatformMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jtang.common.model.auth.PlatformPermission;

import java.util.List;

/**
* <p>
* 菜单表 服务类
* </p>
* @author jtang
* @since 2020-06-30
*/
public interface IPlatformMenuService extends IService<PlatformMenu> {

    List<PlatformMenu> getMenuByUserId(long userId);
}
