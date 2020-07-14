package com.jtang.account.mapper;

import com.jtang.common.model.account.entity.PlatformRoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jtang
 * @since 2020-07-11
 */
public interface PlatformRoleMenuMapper extends BaseMapper<PlatformRoleMenu> {

    /**
     * 根据角色ID列表查询菜单ID
     * @param roleIds 角色ID列表
     * @return long
     * */
    @Select("<script> " +
            "select menu_id from platform_role_menu where role_id in  " +
            "<foreach item='item' index='index' collection='roleIds' open='(' separator=',' close=')'> #{item} </foreach> " +
            "</script>")
    List<Long> getMenuIdByRoleId(@Param("roleIds") List<Long> roleIds);

}
