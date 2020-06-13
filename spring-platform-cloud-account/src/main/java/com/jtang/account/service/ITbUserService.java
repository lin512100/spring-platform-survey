package com.jtang.account.service;

import com.jtang.account.entity.TbUser;
import com.baomidou.mybatisplus.extension.service.IService;
import core.account.UserDetail;

import java.util.List;
import java.util.Map;

/**
* <p>
* 用户 服务类
* </p>
* @author jtang
* @since 2020-06-13
*/
public interface ITbUserService extends IService<TbUser> {

    /**
     * 根据用户名获取用户信息
     * @param username 用户名
     * @return {@link UserDetail}
     * */
    UserDetail loadUserByName(String username);
}
