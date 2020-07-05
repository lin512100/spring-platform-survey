package com.jtang.user.service;

import com.jtang.base.utils.Pagination;
import com.jtang.common.model.account.entity.PlatformUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jtang.common.model.account.response.PlatformUserDTO;
import com.jtang.feign.model.UserDao;
import com.jtang.user.query.PlatformUserQueryDTO;

import java.util.List;

/**
* <p>
* 用户 服务类
* </p>
* @author jtang
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

}
