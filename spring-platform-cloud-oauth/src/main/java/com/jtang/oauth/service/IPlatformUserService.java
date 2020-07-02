package com.jtang.oauth.service;

import com.jtang.common.model.auth.PlatformUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jtang.oauth.model.UserJwt;

/**
* <p>
* 用户 服务类
* </p>
* @author jtang
* @since 2020-06-30
*/
public interface IPlatformUserService extends IService<PlatformUser> {

    UserJwt loadUserByUsername(String username);

}
