package com.jtang.account.controller;

import com.jtang.common.model.account.entity.PlatformPermission;
import com.jtang.common.utils.ResultUtils;
import com.jtang.account.query.PlatformPermissionQueryDTO;
import com.jtang.account.service.IPlatformPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

/**
* 权限 前端控制器
* @author jtang
* @since 2020-06-30
* @version v1.0
*/
@Api(tags = "权限模块")
@RestController
@AllArgsConstructor
@RequestMapping("/permission")
public class PlatformPermissionController {

    @Autowired
    private IPlatformPermissionService service;

    @PostMapping
    @ApiOperation(value = "权限添加")
    public ResultUtils add(@Valid @RequestBody PlatformPermission entity){
        entity.setCreateTime(LocalDateTime.now());
        service.save(entity);
        return ResultUtils.success;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据ID删除信息")
    public ResultUtils delete(@Valid @PathVariable Long id){
        service.getBaseMapper().deleteById(id);
        return ResultUtils.success;
    }

    @PutMapping
    @ApiOperation(value = "修改权限信息")
    public ResultUtils modify(@Valid @RequestBody PlatformPermission entity){
        service.getBaseMapper().updateById(entity);
        return ResultUtils.success;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据ID查询权限信息")
    public ResultUtils detail(@Valid @PathVariable Long id){
        PlatformPermission entity = service.getBaseMapper().selectById(id);
        return ResultUtils.build(entity);
    }

    @GetMapping("/list")
    @ApiOperation(value = "权限列表查询")
    public ResultUtils list(@Valid PlatformPermissionQueryDTO queryDTO) {
        return ResultUtils.build(service.list(queryDTO));
    }

}
