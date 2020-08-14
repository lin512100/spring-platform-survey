package com.jtang.account.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jtang.account.query.PlatformMenuQueryDTO;
import com.jtang.account.service.IPlatformRoleMenuService;
import com.jtang.account.service.InnerService;
import com.jtang.base.client.InnerUrlConstants;
import com.jtang.base.utils.Pagination;
import com.jtang.base.utils.TreeUtils;
import com.jtang.common.model.account.entity.PlatformMenu;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jtang.account.mapper.PlatformMenuMapper;
import com.jtang.account.service.IPlatformMenuService;
import com.jtang.common.model.account.response.PlatformMenuDTO;
import com.jtang.common.service.InitUrlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 菜单表 服务实现类
* @author lin512100
* @since 2020-06-30
*/
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class PlatformMenuServiceImpl extends ServiceImpl<PlatformMenuMapper, PlatformMenu> implements IPlatformMenuService {

    @Override
    public List<PlatformMenu> getMenuByUserId(long userId) {
        return this.baseMapper.getMenuByUserId(userId);
    }

    @Override
    public void deleteMenuById(Long id) {
        this.baseMapper.deleteById(id);
    }

    @Override
    public Pagination<PlatformMenuDTO> getMenuList(PlatformMenuQueryDTO queryDTO) {

        List<PlatformMenuDTO> userInfoList = this.baseMapper.getMenuList(queryDTO);
        queryDTO.setPageIndex(null);
        queryDTO.setPageSize(null);
        Long total = this.baseMapper.getMenuListCount(queryDTO);
        return new Pagination<PlatformMenuDTO>((total == null)?0:total,(userInfoList == null)? new ArrayList<>():userInfoList);
    }

    @Override
    public List<PlatformMenuDTO> tree() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        // 菜单
        List<PlatformMenuDTO> menuList = this.baseMapper.menuTree();
        return TreeUtils.buildByRecursive(menuList, "Id", "Pid", "Children");
    }

    @Override
    public List<PlatformMenuDTO> getTreeById(Long userId) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        List<PlatformMenuDTO> menuList = this.baseMapper.getMenuTree(userId);
        return TreeUtils.buildByRecursive(menuList,"Id","Pid","Children");
    }

    @Override
    public Map<String,Object> asyncOperateUrl(List<HashMap<String, String>> mapList, String server) {
        // 操作URL更改记录
        Map<String,Object> result = new HashMap<>(2);
        List<HashMap<String, String>> add = new ArrayList<>();
        List update = new ArrayList();
        PlatformMenu platformMenu;
        QueryWrapper<PlatformMenu> query;
        // 获取名字为其他的菜单
        QueryWrapper<PlatformMenu> otherQuery = new QueryWrapper<>();
        otherQuery.eq("menu_name","其他");
        PlatformMenu other = getOne(otherQuery);
        //默认其他操作的ID
        long otherId = -1;
        if(other == null){
            other = new PlatformMenu();
            other.setIsMenu(PlatformMenu.IsMenu.YES.getCode());
            other.setMenuName("其他");
            other.setUrl("/other");
            other.setIsShow(PlatformMenu.IsShow.YES.getCode());
            otherId = this.baseMapper.insert(other);
            if(otherId == 1){
                otherId = getOne(otherQuery).getId();
            }

        }

        for(HashMap<String, String> map: mapList){
            // 过滤内部接口
            platformMenu = new PlatformMenu();
            // 类型
            if(!map.containsKey(InitUrlService.REFLEX_TYPE)){
                continue;
            }
            platformMenu.setMethod(map.get(InitUrlService.REFLEX_TYPE));
            // 请求地址
            if(!map.containsKey(InitUrlService.REFLEX_URL) || map.get(InitUrlService.REFLEX_URL).contains("/inner/")){
                continue;
            }
            platformMenu.setUrl(map.get(InitUrlService.REFLEX_URL));
            // 方法描述
            if(!map.containsKey(InitUrlService.REFLEX_API_OPERATION_VALUE)){
                continue;
            }
            platformMenu.setMenuName(map.get(InitUrlService.REFLEX_API_OPERATION_VALUE));
            // 服务名
            platformMenu.setServer(server);

            query = new QueryWrapper<>();
            query.eq("server", platformMenu.getServer());
            query.eq("method", platformMenu.getMethod());
            query.eq("url", platformMenu.getUrl());

            // 查询之前是否有过记录
            PlatformMenu one = getOne(query);
            if(one != null){
                one.setMenuName((one.getMenuName() == null )?"":one.getMenuName());
                // 如果操作名跟数据库的不一致，则更新
                if(!one.getMenuName().equals(platformMenu.getMenuName())){
                    one.setMenuName(platformMenu.getMenuName());
                    updateById(one);
                }
                continue;
            }
            // 如果没有则插入一条
            platformMenu.setPid(otherId);
            save(platformMenu);
            add.add(map);
        }
        result.put("add", add);
        result.put("update",update);
        return result;
    }
}

