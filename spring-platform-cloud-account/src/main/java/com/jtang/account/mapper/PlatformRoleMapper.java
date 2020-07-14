package com.jtang.account.mapper;

import com.jtang.common.model.account.entity.PlatformRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jtang.common.model.account.response.PlatformMenuDTO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 角色 Mapper 接口
 * </p>
 *
 * @author jtang
 * @since 2020-06-30
 */
public interface PlatformRoleMapper extends BaseMapper<PlatformRole> {

    /**
     * 菜单列表
     * @return {@link PlatformMenuDTO}
     * */
    @Select({"select * from platform_menu p_menu"})
    List<PlatformMenuDTO> menuTree();


}
