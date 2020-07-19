package com.jtang.account.mapper;

import com.jtang.common.model.account.entity.PlatformRoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 *  Mapper 接口
 *
 * @author jtang
 * @since 2020-07-11
 */
public interface PlatformRoleMenuMapper extends BaseMapper<PlatformRoleMenu> {

    /**
     * 根据角色ID列表查询非菜单功能ID
     * @param roleIds 角色ID列表
     * @return long
     * */
    List<Long> getMenuIdByRoleId(@Param("roleIds") List<Long> roleIds);

    /**
     * 根据角色ID列表查询非菜单功能ID
     * @param roleIds 角色ID列表
     * @return long
     * */
    List<Long> getALLMenuIdByRoleId(@Param("roleIds") List<Long> roleIds);

}
