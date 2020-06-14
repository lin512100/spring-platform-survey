package com.jtang.account.service;

import com.jtang.account.entity.TbRolePermission;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
* <p>
* 角色权限中间表 服务类
* </p>
* @author jtang
* @since 2020-06-13
*/
public interface ITbRolePermissionService extends IService<TbRolePermission> {

    /**
     * 根据角色ID获取权限ID
     * @param roleId 角色ID
     * @return list
     * */
    List<Long> getPermissionByRoleId(List<Long> roleId);

}
