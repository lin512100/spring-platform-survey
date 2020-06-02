package com.jtang.bussiness.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jtang.business.query.RoleMenuQueryDTO;
import com.jtang.bussiness.entity.RoleMenu;
import com.jtang.bussiness.service.IRoleMenuService;
import com.jtang.web.utils.PageUtils;
import com.jtang.web.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.RestController;

/**
* 角色菜单 前端控制器
* @author jtang
* @since 2020-06-02
* @version v1.0
*/
@Api(tags = "角色菜单模块")
@RestController
@AllArgsConstructor
@RequestMapping("/bussiness/role-menu")
public class RoleMenuController {

    @Autowired
    private IRoleMenuService service;

    @PostMapping
    @ApiOperation(value = "角色菜单添加")
    public ResultUtils addCtFile(@Valid @RequestBody RoleMenu entity){
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
    @ApiOperation(value = "修改角色菜单信息")
    public ResultUtils modifyRoleMenu(@Valid @RequestBody RoleMenu entity){
        service.getBaseMapper().updateById(entity);
        return ResultUtils.success;
    }

    @GetMapping("{id}")
    @ApiOperation(value = "根据ID查询角色菜单信息")
    public ResultUtils getCtFile(@Valid @PathVariable Long id){
        RoleMenu entity = service.getBaseMapper().selectById(id);
        return ResultUtils.build(entity);
    }

    @GetMapping("/list")
    @ApiOperation(value = "角色菜单列表查询")
    public ResultUtils getList(@Valid RoleMenuQueryDTO queryDTO) {
        QueryWrapper<RoleMenu> queryWrapper =  new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        Page<RoleMenu> page = new Page<>(queryDTO.getPageNum(),queryDTO.getPageSize());
        IPage<RoleMenu> iPage = service.getBaseMapper().selectPage(page,queryWrapper);
        return ResultUtils.build(PageUtils.converterToPagination(iPage));
    }

}
