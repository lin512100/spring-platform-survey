package com.jtang.account.service;

import com.jtang.account.query.PlatformRoleMenuDTO;
import com.jtang.common.model.account.entity.PlatformRoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* <p>
*  服务类
* </p>
* @author jtang
* @since 2020-07-11
*/
public interface IPlatformRoleMenuService extends IService<PlatformRoleMenu> {

    /**
     * 根据角色ID列表查询菜单ID
     * @param roleIds 权限IDs
     * @return long
     * */
    List<Long> getMenuIdByRoleId(List<Long> roleIds);


    /**
     * 根据角色ID和菜单ID更新权限
     * @param entity {@link PlatformRoleMenuDTO}
     * */
    void modifyRoleMenu(PlatformRoleMenuDTO entity);

}
