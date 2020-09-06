package com.jtang.system.mapper;

import com.jtang.common.model.account.response.PlatformMenuDTO;
import com.jtang.system.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author jtang
 * @since 2020-09-06
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 获取用户权限菜单列表
     * @param userId 用户ID
     * @return {@link PlatformMenuDTO}
     * */
    List<PlatformMenuDTO> getMenuTree(@Param("userId") Long userId);

}
