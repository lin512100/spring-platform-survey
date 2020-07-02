package com.jtang.oauth.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jtang.business.query.PlatformUserRoleQueryDTO;
import com.jtang.common.model.oauth.entity.PlatformUserRole;
import com.jtang.common.utils.ResultUtils;
import com.jtang.oauth.service.IPlatformUserRoleService;
import com.jtang.web.utils.PageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.RestController;

/**
* 用户角色 前端控制器
* @author jtang
* @since 2020-06-30
* @version v1.0
*/
@Api(tags = "用户角色模块")
@RestController
@AllArgsConstructor
@RequestMapping("/oauth/platform-user-role")
public class PlatformUserRoleController {

    @Autowired
    private IPlatformUserRoleService service;

    @PostMapping
    @ApiOperation(value = "用户角色添加")
    public ResultUtils addCtFile(@Valid @RequestBody PlatformUserRole entity){
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
    @ApiOperation(value = "修改用户角色信息")
    public ResultUtils modifyPlatformUserRole(@Valid @RequestBody PlatformUserRole entity){
        service.getBaseMapper().updateById(entity);
        return ResultUtils.success;
    }

    @GetMapping("{id}")
    @ApiOperation(value = "根据ID查询用户角色信息")
    public ResultUtils getCtFile(@Valid @PathVariable Long id){
        PlatformUserRole entity = service.getBaseMapper().selectById(id);
        return ResultUtils.build(entity);
    }

    @GetMapping("/list")
    @ApiOperation(value = "用户角色列表查询")
    public ResultUtils getList(@Valid PlatformUserRoleQueryDTO queryDTO) {
        QueryWrapper<PlatformUserRole> queryWrapper =  new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        Page<PlatformUserRole> page = new Page<>(queryDTO.getPageNum(),queryDTO.getPageSize());
        IPage<PlatformUserRole> iPage = service.getBaseMapper().selectPage(page,queryWrapper);
        return ResultUtils.build(PageUtils.converterToPagination(iPage));
    }

}
