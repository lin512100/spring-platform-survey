package com.jtang.account.service;

import com.jtang.common.model.account.entity.PlatformRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jtang.common.model.account.response.MenuTree;

import java.util.List;

/**
* <p>
* 角色 服务类
* </p>
* @author jtang
* @since 2020-06-30
*/
public interface IPlatformRoleService extends IService<PlatformRole> {

    /**
     * 更改用户权限
     * */
    List<MenuTree> getMenuTree();

}
