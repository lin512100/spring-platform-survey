package com.jtang.feign.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @date 2020/6/30 21:45
 * @author LinJinTang
 */
@ToString
@Getter
@Setter
public class UserJwt extends User {

    private Long id;
    private String name;

    public UserJwt(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }


}
