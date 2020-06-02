package com.jtang.bussiness.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jtang.business.query.UserRoleQueryDTO;
import com.jtang.bussiness.entity.UserRole;
import com.jtang.bussiness.service.IUserRoleService;
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
* 角色用户 前端控制器
* @author jtang
* @since 2020-06-02
* @version v1.0
*/
@Api(tags = "角色用户模块")
@RestController
@AllArgsConstructor
@RequestMapping("/bussiness/user-role")
public class UserRoleController {

    @Autowired
    private IUserRoleService service;

    @PostMapping
    @ApiOperation(value = "角色用户添加")
    public ResultUtils addCtFile(@Valid @RequestBody UserRole entity){
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
    @ApiOperation(value = "修改角色用户信息")
    public ResultUtils modifyUserRole(@Valid @RequestBody UserRole entity){
        service.getBaseMapper().updateById(entity);
        return ResultUtils.success;
    }

    @GetMapping("{id}")
    @ApiOperation(value = "根据ID查询角色用户信息")
    public ResultUtils getCtFile(@Valid @PathVariable Long id){
        UserRole entity = service.getBaseMapper().selectById(id);
        return ResultUtils.build(entity);
    }

    @GetMapping("/list")
    @ApiOperation(value = "角色用户列表查询")
    public ResultUtils getList(@Valid UserRoleQueryDTO queryDTO) {
        QueryWrapper<UserRole> queryWrapper =  new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        Page<UserRole> page = new Page<>(queryDTO.getPageNum(),queryDTO.getPageSize());
        IPage<UserRole> iPage = service.getBaseMapper().selectPage(page,queryWrapper);
        return ResultUtils.build(PageUtils.converterToPagination(iPage));
    }

}
