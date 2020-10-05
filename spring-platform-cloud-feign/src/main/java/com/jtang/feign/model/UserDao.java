package com.jtang.feign.model;

import lombok.*;

import java.util.List;

/**
 * @date 2020/7/4 18:49
 * @author LinJinTang
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDao {
    private long id;
    private String name;
    private String username;
    private String password;
    private String salt;
    private List<String> authority;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    public UserDao(List<String> authority){
        this.authority = authority;
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }
}
