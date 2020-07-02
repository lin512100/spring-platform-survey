package com.jtang.oauth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jtang.common.model.auth.PlatformMenu;
import com.jtang.common.model.auth.PlatformPermission;
import com.jtang.common.model.auth.PlatformUser;
import com.jtang.oauth.mapper.PlatformPermissionMapper;
import com.jtang.oauth.mapper.PlatformUserMapper;
import com.jtang.oauth.model.UserJwt;
import com.jtang.oauth.service.IPlatformMenuService;
import com.jtang.oauth.service.IPlatformPermissionService;
import com.jtang.oauth.service.IPlatformUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
* <p>
* 用户 服务实现类
* </p>
*
* @author jtang
* @since 2020-06-30
*/
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class PlatformUserServiceImpl extends ServiceImpl<PlatformUserMapper, PlatformUser> implements IPlatformUserService {

    @Autowired
    private IPlatformMenuService iPlatformMenuService;

    @Override
    public UserJwt loadUserByUsername(String username) {
        // 根据用户名称查询用户账户信息
        QueryWrapper<PlatformUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        PlatformUser user = getOne(queryWrapper);
        if(user == null){
            throw new UsernameNotFoundException("用户不存在");
        }
        // 查询用户权限信息
        List<PlatformMenu> userPermissionByUserId = iPlatformMenuService.getMenuByUserId(user.getId());
        List<String> collect = userPermissionByUserId.stream().map(PlatformMenu::getUrl).collect(Collectors.toList());
        String menus  = StringUtils.join(collect, ",");
        UserJwt userDetails = new UserJwt(username,
                user.getPassword(),
                AuthorityUtils.commaSeparatedStringToAuthorityList(menus));
        userDetails.setId(user.getId().toString());
        return userDetails;
    }
}

