package com.jtang.system.service;

import com.jtang.system.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
*  服务类
* @author jtang
* @since 2020-09-06
*/
public interface ISysUserService extends IService<SysUser> {

    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * */
    SysUser findByUsername(String username);

}
