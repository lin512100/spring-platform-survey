package com.jtang.account.service;

import com.jtang.base.utils.Pagination;
import com.jtang.common.model.account.entity.PlatformUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jtang.common.model.account.response.PlatformUserDTO;
import com.jtang.feign.model.UserDao;
import com.jtang.account.query.PlatformUserQueryDTO;

/**
* 用户 服务类
* @author lin512100
* @since 2020-06-30
*/
public interface IPlatformUserService extends IService<PlatformUser> {

    UserDao loadUserByUsername(String username);

    /**
     * 查询用户信息列表
     * @param queryDTO {@link PlatformUserQueryDTO}
     * @return {@link Pagination}
     * */
    Pagination getUserInfoList(PlatformUserQueryDTO queryDTO);

    /**
     * 用户信息修改
     * */
    void updateUserInfo(PlatformUserDTO platformUserDTO);

    /**
     * 用户信息新增
     * */
    void addUserInfo(PlatformUserDTO platformUserDTO);

    /**
     * 删除用户信息
     * */
    void delUserInfo(String ids);

}
