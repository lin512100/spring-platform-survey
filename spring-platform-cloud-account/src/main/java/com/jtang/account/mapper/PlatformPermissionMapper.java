package com.jtang.account.mapper;

import com.jtang.account.query.PlatformPermissionQueryDTO;
import com.jtang.common.model.account.entity.PlatformPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jtang.common.model.account.response.PlatformMenuDTO;
import com.jtang.common.model.account.response.PlatformPermissionDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 权限 Mapper 接口
 * </p>
 *
 * @author jtang
 * @since 2020-06-30
 */
public interface PlatformPermissionMapper extends BaseMapper<PlatformPermission> {


    /**
     * 查询操作信息列表
     * @param queryDTO {@link PlatformPermissionQueryDTO}
     * */
    List<PlatformPermissionDTO> list(@Param("queryDTO") PlatformPermissionQueryDTO queryDTO);

    /**
     * 菜单操作列表
     * @return {@link PlatformMenuDTO}
     * */
    List<PlatformMenuDTO> menuPermissionList();


}
