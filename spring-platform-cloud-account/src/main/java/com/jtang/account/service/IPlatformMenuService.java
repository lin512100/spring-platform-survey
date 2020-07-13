package com.jtang.account.service;

import com.jtang.account.query.PlatformMenuQueryDTO;
import com.jtang.base.utils.Pagination;
import com.jtang.common.model.account.entity.PlatformMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jtang.common.model.account.response.MenuTree;
import com.jtang.common.model.account.response.PlatformMenuDTO;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
* <p>
* 菜单表 服务类
* </p>
* @author jtang
* @since 2020-06-30
*/
public interface IPlatformMenuService extends IService<PlatformMenu> {

    /**
     * 通过用户ID获取菜单列表
     * */
    List<PlatformMenu> getMenuByUserId(long userId);

    /**
     * 通过用户ID获取菜单列表
     * @param userId 用户ID
     * @return {@link PlatformMenu}
     * */
    List<PlatformMenu> getListByRole(long userId);


    /**
     * 菜单列表信息查询
     * @param queryDTO {@link PlatformMenuQueryDTO}
     * @return {@link PlatformMenuDTO}
     * */
    Pagination<PlatformMenuDTO> getMenuList(PlatformMenuQueryDTO queryDTO);

    /**
     * 菜单树结构
     * */
    List<MenuTree> tree(List<Long> roleId) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;

}
