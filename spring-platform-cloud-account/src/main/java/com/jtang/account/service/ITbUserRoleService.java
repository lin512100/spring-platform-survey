package com.jtang.account.service;

import com.jtang.account.entity.TbUserRole;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
* <p>
* 用户角色 服务类
* </p>
* @author jtang
* @since 2020-06-13
*/
public interface ITbUserRoleService extends IService<TbUserRole> {

    /**
     * 根据用户ID查询角色列表
     * @param userId 用户ID
     * @return {@link TbUserRole}
     * */
    List<Long> getRoleIdByUserId(long userId);

}
