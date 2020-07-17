package com.jtang.account.mapper;

import com.jtang.account.query.PlatformMenuQueryDTO;
import com.jtang.common.model.account.entity.PlatformMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jtang.common.model.account.response.PlatformMenuDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 菜单表 Mapper 接口
 * @author jtang
 * @since 2020-06-30
 */
public interface PlatformMenuMapper extends BaseMapper<PlatformMenu> {

    /**
     * 根据用户ID查询用户权限菜单列表
     * @param userId 用户ID
     * @return {@link PlatformMenu}
     * */
    List<PlatformMenu> getMenuByUserId(@Param("userId") long userId);

    /**
     * 菜单列表信息查询
     * @param queryDTO {@link PlatformMenuQueryDTO}
     * @return {@link PlatformMenuDTO}
     * */
    List<PlatformMenuDTO> getMenuList(@Param("queryDTO") PlatformMenuQueryDTO queryDTO);

    /**
     * 菜单列表信息查询
     * @param queryDTO {@link PlatformMenuQueryDTO}
     * @return {@link PlatformMenuDTO}
     * */
    Long getMenuListCount(@Param("queryDTO") PlatformMenuQueryDTO queryDTO);

    /**
     * 获取树结构所有数据
     * @return {@link PlatformMenuDTO}
     * */
    @Select("SELECT p_menu.id, p_menu.pid, p_menu.menu_name AS menuName FROM platform_menu p_menu")
    List<PlatformMenuDTO> menuTree();

    /** 获取用户权限菜单列表 */
    List<PlatformMenuDTO> getMenuTree(@Param("userId") Long userId);
}
