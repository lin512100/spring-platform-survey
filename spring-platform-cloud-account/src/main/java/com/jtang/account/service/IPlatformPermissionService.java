package com.jtang.account.service;

import com.jtang.account.query.PlatformPermissionQueryDTO;
import com.jtang.base.utils.Pagination;
import com.jtang.common.model.account.entity.PlatformPermission;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jtang.common.model.account.response.MenuTree;
import com.jtang.common.model.account.response.PlatformPermissionDTO;

import java.util.List;

/**
* <p>
* 权限 服务类
* </p>
* @author jtang
* @since 2020-06-30
*/
public interface IPlatformPermissionService extends IService<PlatformPermission> {

    /**
     * 查询操作信息列表
     * @param queryDTO {@link PlatformPermissionQueryDTO}
     * @return {@link Pagination}
     * */
    Pagination<PlatformPermissionDTO> list(PlatformPermissionQueryDTO queryDTO);

    /**
     * 菜单操作列表
     * @return {@link MenuTree}
     * */
    List<MenuTree> menuPermissionList();

}
