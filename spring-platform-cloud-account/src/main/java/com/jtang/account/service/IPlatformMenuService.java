package com.jtang.account.service;

import com.jtang.account.query.PlatformMenuQueryDTO;
import com.jtang.base.utils.Pagination;
import com.jtang.common.model.account.entity.PlatformMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jtang.common.model.account.response.PlatformMenuDTO;
import com.jtang.common.model.account.response.PlatformMenuDTO;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 菜单表 服务类
* @author jtang
* @since 2020-06-30
*/
public interface IPlatformMenuService extends IService<PlatformMenu> {

    /**
     * 通过用户ID获取菜单列表
     * */
    List<PlatformMenu> getMenuByUserId(long userId);

    /**
     * 删除菜单信息
     * @param id 菜单Id
     * */
    void deleteMenuById(Long id);

    /**
     * 菜单列表信息查询
     * @param queryDTO {@link PlatformMenuQueryDTO}
     * @return {@link PlatformMenuDTO}
     * */
    Pagination<PlatformMenuDTO> getMenuList(PlatformMenuQueryDTO queryDTO);

    /**
     * 菜单树结构
     * */
    List<PlatformMenuDTO> tree() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;

    /**
     * 根据用户ID查询树状图结构
     * @param userId 用户ID
     * */
    List<PlatformMenuDTO> getTreeById(Long userId)  throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;

    /**
     * 同步URL请求接口信息
     * @param mapList 请求接口信息
     * @return Map<String,Object>
     * @param server 服务名
     * */
    Map<String,Object> asyncOperateUrl(List<HashMap<String, String>> mapList, String server);

}
