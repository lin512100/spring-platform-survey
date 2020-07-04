package com.jtang.user.service;

import com.jtang.common.model.oauth.entity.PlatformUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jtang.feign.model.UserDao;

/**
* <p>
* 用户 服务类
* </p>
* @author jtang
* @since 2020-06-30
*/
public interface IPlatformUserService extends IService<PlatformUser> {

    UserDao loadUserByUsername(String username);

}
