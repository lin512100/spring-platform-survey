package com.jtang.account.service;

import com.jtang.account.query.PlatformRoleMenuDTO;
import com.jtang.common.model.account.entity.PlatformRoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jtang.common.model.account.response.HandleAllow;

import java.util.List;

/**
*  服务类
* @author jtang
* @since 2020-07-11
*/
public interface IPlatformRoleMenuService extends IService<PlatformRoleMenu> {

    /**
     * 根据角色ID列表查询资源权限ID
     * @param roleIds 权限IDs
     * @return long
     * */
    List<Long> getMenuIdByRoleId(List<Long> roleIds);

    /**
     * 根据角色ID列表查询所有ID
     * @param roleIds 权限IDs
     * @return long
     * */
    List<Long> getAllMenuIdByRoleId(List<Long> roleIds);
    /**
     * 根据角色ID和菜单ID更新权限
     * @param entity {@link PlatformRoleMenuDTO}
     * */
    void modifyRoleMenu(PlatformRoleMenuDTO entity);

    /**
     * 根据角色ID查询角色操作清单列表
     * @param roleIds 角色ID
     * @return {@link HandleAllow}
     * */
    List<HandleAllow> getHandleAllow(List<Long> roleIds);

}
