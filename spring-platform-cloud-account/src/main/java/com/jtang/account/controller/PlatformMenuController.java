package com.jtang.account.controller;

import com.jtang.account.service.IPlatformRoleMenuService;
import com.jtang.common.annotation.OperationLog;
import com.jtang.common.model.account.entity.PlatformMenu;
import com.jtang.base.utils.ResultUtils;
import com.jtang.account.query.PlatformMenuQueryDTO;
import com.jtang.account.service.IPlatformMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.*;

/**
* 菜单表 前端控制器
* @author lin512100
* @since 2020-06-30
* @version v1.0
*/
@Slf4j
@Api(tags = "菜单表模块")
@RestController
@AllArgsConstructor
@RequestMapping("/menu")
@OperationLog
public class PlatformMenuController {

    @Autowired
    private IPlatformMenuService service;

    @Autowired
    private IPlatformRoleMenuService iPlatformRoleMenuService;

    @PostMapping
    @ApiOperation(value = "菜单表添加")
    public ResultUtils add(@Valid @RequestBody PlatformMenu entity){
        entity.setCreateTime(LocalDateTime.now());
        entity.setUpdateTime(LocalDateTime.now());
        service.save(entity);
        return ResultUtils.success;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据ID删除信息")
    public ResultUtils delete(@Valid @PathVariable Long id){
        service.deleteMenuById(id);
        return ResultUtils.success;
    }

    @PutMapping
    @ApiOperation(value = "修改菜单表信息")
    public ResultUtils modify(@Valid @RequestBody PlatformMenu entity){
        service.getBaseMapper().updateById(entity);
        return ResultUtils.success;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据ID查询菜单表信息")
    public ResultUtils detail(@Valid @PathVariable Long id){
        PlatformMenu entity = service.getBaseMapper().selectById(id);
        return ResultUtils.build(entity);
    }

    @GetMapping("/list")
    @ApiOperation(value = "菜单表列表查询")
    public ResultUtils list(@Valid PlatformMenuQueryDTO queryDTO) {
        return ResultUtils.build(service.getMenuList(queryDTO));

    }

    @GetMapping("/public/tree/{roleId}")
    @ApiOperation(value = "菜单树结构")
    public ResultUtils tree(@PathVariable("roleId") Long roleId) throws Exception{
        // 默认权限
        List<Long> menuIdByRoleId = iPlatformRoleMenuService.getMenuIdByRoleId(Collections.singletonList(roleId));
        Map<String,List> result = new HashMap<>(2);
        result.put("selected",menuIdByRoleId);
        result.put("menu",service.tree());
        return ResultUtils.build(result);
    }

    @GetMapping("/public/getTreeById/{userId}")
    @ApiOperation(value = "根据用户ID查询树状图结构")
    public ResultUtils getTreeById(@PathVariable("userId") Long userId) throws Exception{
        return ResultUtils.build(service.getTreeById(userId));
    }

}
