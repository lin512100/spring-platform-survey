package com.jtang.oauth.service.impl;

import com.jtang.feign.model.UserDao;
import com.jtang.feign.model.UserJwt;
import com.jtang.feign.service.UserApiService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Service;

/**
 * @date 2020/7/1 21:45
 * @author LinJinTang
 */
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private UserApiService userApiService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //取出身份，如果身份为空说明没有认证
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //没有认证统一采用http basic认证，http basic中存储了client_id和client_secret，开始认证client_id和client_secret
        if(authentication == null){
            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(username);
            if(clientDetails != null){
                //密码
                String clientSecret = clientDetails.getClientSecret();
                return new User(username,clientSecret, AuthorityUtils.commaSeparatedStringToAuthorityList("aa,aa"));
            }
        }
        if (StringUtils.isEmpty(username)) {
            throw new UsernameNotFoundException("用户名不能为空");
        }
        UserDao user = userApiService.getUsernameInfo(username);
        UserJwt userJwt = new UserJwt(user.getUsername(), user.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList(StringUtils.join(user.getAuthority(), ",")));
        BeanUtils.copyProperties(user,userJwt);
        return userJwt;
    }
}
