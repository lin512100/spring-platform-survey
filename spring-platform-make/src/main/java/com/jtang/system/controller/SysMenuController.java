package com.jtang.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jtang.system.entity.SysMenu;
import com.jtang.system.query.SysMenuQueryDTO;
import com.jtang.system.service.ISysMenuService;
import com.jtang.web.utils.PageUtils;
import com.jtang.base.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.RestController;

/**
* 菜单表 前端控制器
* @author jtang
* @since 2020-09-06
* @version v1.0
*/
@Api(tags = "菜单表模块")
@RestController
@AllArgsConstructor
@RequestMapping("/system/menu")
public class SysMenuController {

    @Autowired
    private ISysMenuService service;

    @PostMapping
    @ApiOperation(value = "菜单表添加")
    public ResultUtils addCtFile(@Valid @RequestBody SysMenu entity){
        service.save(entity);
        return ResultUtils.success;
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "根据ID删除信息")
    public ResultUtils addCtFile(@Valid @PathVariable Long id){
        service.getBaseMapper().deleteById(id);
        return ResultUtils.success;
    }

    @PutMapping
    @ApiOperation(value = "修改菜单表信息")
    public ResultUtils modifySysMenu(@Valid @RequestBody SysMenu entity){
        service.getBaseMapper().updateById(entity);
        return ResultUtils.success;
    }

    @GetMapping("{id}")
    @ApiOperation(value = "根据ID查询菜单表信息")
    public ResultUtils getCtFile(@Valid @PathVariable Long id){
        SysMenu entity = service.getBaseMapper().selectById(id);
        return ResultUtils.build(entity);
    }

    @GetMapping("/list")
    @ApiOperation(value = "菜单表列表查询")
    public ResultUtils getList(@Valid SysMenuQueryDTO queryDTO) {
        QueryWrapper<SysMenu> queryWrapper =  new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        Page<SysMenu> page = new Page<>(queryDTO.getPageIndex(),queryDTO.getPageSize());
        IPage<SysMenu> iPage = service.getBaseMapper().selectPage(page,queryWrapper);
        return ResultUtils.build(PageUtils.converterToPagination(iPage));
    }

    @GetMapping("/getTreeById/{userId}")
    @ApiOperation(value = "根据用户ID查询树状图结构")
    public ResultUtils getTreeById(@PathVariable("userId") Long userId) throws Exception{
        return ResultUtils.build(service.getTreeById(userId));
    }

}
