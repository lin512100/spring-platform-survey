package com.jtang.account.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jtang.account.query.TbRolePermissionQueryDTO;
import com.jtang.account.entity.TbRolePermission;
import com.jtang.account.service.ITbRolePermissionService;
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
* 角色权限中间表 前端控制器
* @author jtang
* @since 2020-06-13
* @version v1.0
*/
@Api(tags = "角色权限中间表模块")
@RestController
@AllArgsConstructor
@RequestMapping("/account/tb-role-permission")
public class TbRolePermissionController {

    @Autowired
    private ITbRolePermissionService service;

    @PostMapping
    @ApiOperation(value = "角色权限中间表添加")
    public ResultUtils addCtFile(@Valid @RequestBody TbRolePermission entity){
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
    @ApiOperation(value = "修改角色权限中间表信息")
    public ResultUtils modifyTbRolePermission(@Valid @RequestBody TbRolePermission entity){
        service.getBaseMapper().updateById(entity);
        return ResultUtils.success;
    }

    @GetMapping("{id}")
    @ApiOperation(value = "根据ID查询角色权限中间表信息")
    public ResultUtils getCtFile(@Valid @PathVariable Long id){
        TbRolePermission entity = service.getBaseMapper().selectById(id);
        return ResultUtils.build(entity);
    }

    @GetMapping("/list")
    @ApiOperation(value = "角色权限中间表列表查询")
    public ResultUtils getList(@Valid TbRolePermissionQueryDTO queryDTO) {
        QueryWrapper<TbRolePermission> queryWrapper =  new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        Page<TbRolePermission> page = new Page<>(queryDTO.getPageNum(),queryDTO.getPageSize());
        IPage<TbRolePermission> iPage = service.getBaseMapper().selectPage(page,queryWrapper);
        return ResultUtils.build(PageUtils.converterToPagination(iPage));
    }

}
