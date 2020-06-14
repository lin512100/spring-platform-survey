package com.jtang.account.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jtang.account.query.TbPermissionQueryDTO;
import com.jtang.account.entity.TbPermission;
import com.jtang.account.service.ITbPermissionService;
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
* 权限 前端控制器
* @author jtang
* @since 2020-06-13
* @version v1.0
*/
@Api(tags = "权限模块")
@RestController
@AllArgsConstructor
@RequestMapping("/account/tb-permission")
public class TbPermissionController {

    @Autowired
    private ITbPermissionService service;

    @PostMapping
    @ApiOperation(value = "权限添加")
    public ResultUtils addCtFile(@Valid @RequestBody TbPermission entity){
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
    @ApiOperation(value = "修改权限信息")
    public ResultUtils modifyTbPermission(@Valid @RequestBody TbPermission entity){
        service.getBaseMapper().updateById(entity);
        return ResultUtils.success;
    }

    @GetMapping("{id}")
    @ApiOperation(value = "根据ID查询权限信息")
    public ResultUtils getCtFile(@Valid @PathVariable Long id){
        TbPermission entity = service.getBaseMapper().selectById(id);
        return ResultUtils.build(entity);
    }

    @GetMapping("/list")
    @ApiOperation(value = "权限列表查询")
    public ResultUtils getList(@Valid TbPermissionQueryDTO queryDTO) {
        QueryWrapper<TbPermission> queryWrapper =  new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        Page<TbPermission> page = new Page<>(queryDTO.getPageNum(),queryDTO.getPageSize());
        IPage<TbPermission> iPage = service.getBaseMapper().selectPage(page,queryWrapper);
        return ResultUtils.build(PageUtils.converterToPagination(iPage));
    }

}
