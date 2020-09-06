package com.jtang.core.config;

import com.jtang.base.exception.ExceptionCast;
import com.jtang.common.model.account.response.AuthCode;
import com.jtang.system.entity.SysUser;
import com.jtang.system.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户登录实现类
 * @author lin512100
 * @date 2020/9/5
 */
@Slf4j
@Service
public class UserLoginServiceImpl implements UserDetailsService {

    @Autowired
    private ISysUserService iSysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = iSysUserService.findByUsername(username);
        if(user == null){
            ExceptionCast.cast(AuthCode.AUTH_ACCOUNT_NOT_EXISTS);
        }
        return user;
    }

    /** TODO 后面完善 */
    public List<GrantedAuthority> getGrantedAuthority(){
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return list;
    }
}
