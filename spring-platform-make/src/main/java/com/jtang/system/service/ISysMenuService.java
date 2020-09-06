package com.jtang.system.service;

import com.jtang.common.model.account.response.PlatformMenuDTO;
import com.jtang.system.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
* 菜单表 服务类
* @author jtang
* @since 2020-09-06
*/
public interface ISysMenuService extends IService<SysMenu> {

    /**
     * 根据用户ID查询树状图结构
     * @param userId 用户ID
     * */
    List<PlatformMenuDTO> getTreeById(Long userId)  throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;

}
