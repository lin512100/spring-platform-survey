package com.jtang.oauth.service.impl;

import com.jtang.dto.UserDetail;
import com.jtang.oauth.service.AccountService;
import core.account.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @date 2020/6/14 9:38
 * @author LinJinTang
 */
@Slf4j
@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserInfo userInfo = accountService.getUserDetail(s);
        if(userInfo == null || userInfo.getUsername() == null){
            throw new UsernameNotFoundException("用户信息不存在");
        }
        UserDetail userDetail = new UserDetail();
        BeanUtils.copyProperties(userInfo, userDetail);
        // 权限集合
        List<GrantedAuthority> grantedAuthorities = userInfo.getRole().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        userDetail.setAuthorities(grantedAuthorities);
        return userDetail;
    }
}
