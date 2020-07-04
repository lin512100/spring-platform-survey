package com.jtang.oauth.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jtang.common.model.oauth.entity.PlatformPermission;
import com.jtang.common.utils.ResultUtils;
import com.jtang.oauth.query.PlatformPermissionQueryDTO;
import com.jtang.oauth.service.IPlatformPermissionService;
import com.jtang.web.utils.PageUtils;
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
* @since 2020-06-30
* @version v1.0
*/
@Api(tags = "权限模块")
@RestController
@AllArgsConstructor
@RequestMapping("/oauth/platform-permission")
public class PlatformPermissionController {

    @Autowired
    private IPlatformPermissionService service;

    @PostMapping
    @ApiOperation(value = "权限添加")
    public ResultUtils addCtFile(@Valid @RequestBody PlatformPermission entity){
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
    public ResultUtils modifyPlatformPermission(@Valid @RequestBody PlatformPermission entity){
        service.getBaseMapper().updateById(entity);
        return ResultUtils.success;
    }

    @GetMapping("{id}")
    @ApiOperation(value = "根据ID查询权限信息")
    public ResultUtils getCtFile(@Valid @PathVariable Long id){
        PlatformPermission entity = service.getBaseMapper().selectById(id);
        return ResultUtils.build(entity);
    }

    @GetMapping("/list")
    @ApiOperation(value = "权限列表查询")
    public ResultUtils getList(@Valid PlatformPermissionQueryDTO queryDTO) {
        QueryWrapper<PlatformPermission> queryWrapper =  new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        Page<PlatformPermission> page = new Page<>(queryDTO.getPageNum(),queryDTO.getPageSize());
        IPage<PlatformPermission> iPage = service.getBaseMapper().selectPage(page,queryWrapper);
        return ResultUtils.build(PageUtils.converterToPagination(iPage));
    }

}
