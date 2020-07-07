package com.jtang.account.mapper;

import com.jtang.common.model.account.entity.PlatformUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jtang.common.model.account.response.PlatformUserDTO;
import com.jtang.account.query.PlatformUserQueryDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author jtang
 * @since 2020-06-30
 */
public interface PlatformUserMapper extends BaseMapper<PlatformUser> {

    /**
     * 根据条件查询用户信息列表
     * @param queryDTO 查询类
     * @return {@link PlatformUserDTO}
     * */
    List<PlatformUserDTO> getUserInfoList(@Param("queryDTO") PlatformUserQueryDTO queryDTO);

}
