package com.jtang.oauth.mapper;

import com.jtang.common.model.oauth.entity.PlatformMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author jtang
 * @since 2020-06-30
 */
public interface PlatformMenuMapper extends BaseMapper<PlatformMenu> {

    /**
     * 根据用户ID查询用户权限菜单列表
     * @param userId 用户ID
     * */
    List<PlatformMenu> getMenuByUserId(@Param("userId") long userId);
}
