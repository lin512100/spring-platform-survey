package com.jtang.account.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jtang.account.service.IPlatformUserRoleService;
import com.jtang.base.exception.BusinessException;
import com.jtang.base.exception.ExceptionCast;
import com.jtang.base.utils.EncryptionUtils;
import com.jtang.base.utils.Pagination;
import com.jtang.base.utils.UIDUtils;
import com.jtang.common.model.account.entity.PlatformMenu;
import com.jtang.common.model.account.entity.PlatformUser;
import com.jtang.common.model.account.entity.PlatformUserRole;
import com.jtang.common.model.account.response.PlatformUserDTO;
import com.jtang.feign.model.UserDao;
import com.jtang.account.mapper.PlatformUserMapper;
import com.jtang.account.query.PlatformUserQueryDTO;
import com.jtang.account.service.IPlatformMenuService;
import com.jtang.account.service.IPlatformUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
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

    @Autowired
    private IPlatformUserRoleService iPlatformUserRoleService;

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

    @Override
    public void updateUserInfo(PlatformUserDTO platformUserDTO) {
        PlatformUser platformUser = this.baseMapper.selectById(platformUserDTO.getId());
        if(platformUser == null){
            throw new BusinessException("用户信息不存在");
        }
        BeanUtils.copyProperties(platformUserDTO,platformUser);
        // 更新用户基础信息
        this.baseMapper.updateById(platformUser);

        // 删除旧角色
        QueryWrapper<PlatformUserRole> roleQueryWrapper = new QueryWrapper<>();
        roleQueryWrapper.eq("user_id", platformUserDTO.getId());
        iPlatformUserRoleService.remove(roleQueryWrapper);

        // 关联新角色
        addRole(platformUserDTO.getId(),platformUserDTO.getRoleId());
    }

    @Override
    public void addUserInfo(PlatformUserDTO platformUserDTO) {
        PlatformUser platformUser = new PlatformUser();
        BeanUtils.copyProperties(platformUserDTO,platformUser);
        // 盐值
        String salt = EncryptionUtils.getSalt();
        platformUser.setSalt(salt);
        // 默认初始密码
        platformUser.setPassword(EncryptionUtils.getPBKDF2Code("123456",salt));
        // 保存用户信息
        long id = this.getBaseMapper().insert(platformUser);
        // 关联新角色
        addRole(id,platformUserDTO.getRoleId());
    }


    /** 关联新角色*/
    private void addRole(Long userId,String roleIds){
        List<PlatformUserRole> list = new ArrayList<>();
        List<Long> collect = Arrays.stream(roleIds.split(",")).map(Long::parseLong).collect(Collectors.toList());
        for (Long roleId: collect) {
            list.add(new PlatformUserRole(userId,roleId));
        }
        // 添加新建的权限
        iPlatformUserRoleService.saveBatch(list);
    }

}

