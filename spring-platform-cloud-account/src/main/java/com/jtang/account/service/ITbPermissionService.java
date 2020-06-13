package com.jtang.account.service;

import com.jtang.account.entity.TbPermission;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import java.util.Map;

/**
* 权限 服务类
* @author jtang
* @since 2020-06-13
*/
public interface ITbPermissionService extends IService<TbPermission> {

    /**
     * 根据角色ID列表查询权限ID列表
     * @param permissionId 权限ID
     * @return list {@link TbPermission}
     * */
    List<TbPermission> getListById(List<Long> permissionId);

}
