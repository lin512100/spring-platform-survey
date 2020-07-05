package com.jtang.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jtang.base.utils.Pagination;
import com.jtang.common.model.account.entity.PlatformMenu;
import com.jtang.common.model.account.entity.PlatformUser;
import com.jtang.common.model.account.response.PlatformUserDTO;
import com.jtang.feign.model.UserDao;
import com.jtang.user.mapper.PlatformUserMapper;
import com.jtang.user.query.PlatformUserQueryDTO;
import com.jtang.user.service.IPlatformMenuService;
import com.jtang.user.service.IPlatformUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public UserDao loadUserByUsername(String username) {
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
        UserDao userDao = new UserDao(collect);
        BeanUtils.copyProperties(user,userDao);
        return userDao;
    }

    @Override
    public Pagination<PlatformUserDTO> getUserInfoList(PlatformUserQueryDTO queryDTO) {
        List<PlatformUserDTO> userInfoList = this.baseMapper.getUserInfoList(queryDTO);
        queryDTO.setPageNum(null);
        queryDTO.setPageSize(null);
        List<PlatformUserDTO> total = this.baseMapper.getUserInfoList(queryDTO);
        return new Pagination<PlatformUserDTO>((total == null)?0:total.size(),(userInfoList == null)? new ArrayList<>():userInfoList);
    }

}

