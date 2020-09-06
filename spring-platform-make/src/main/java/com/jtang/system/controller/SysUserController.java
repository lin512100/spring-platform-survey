package com.jtang.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jtang.base.enums.UserStatusEnums;
import com.jtang.common.annotation.OperationLog;
import com.jtang.system.entity.SysUser;
import com.jtang.system.query.SysUserQueryDTO;
import com.jtang.system.service.ISysUserService;
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
*  前端控制器
* @author jtang
* @since 2020-09-06
* @version v1.0
*/
@Api(tags = "模块")
@RestController
@AllArgsConstructor
@RequestMapping("/system/user")
public class SysUserController {

    @Autowired
    private ISysUserService service;

    @PostMapping
    @ApiOperation(value = "添加")
    public ResultUtils addCtFile(@Valid @RequestBody SysUser entity){
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
    @ApiOperation(value = "修改信息")
    public ResultUtils modifySysUser(@Valid @RequestBody SysUser entity){
        service.getBaseMapper().updateById(entity);
        return ResultUtils.success;
    }

    @GetMapping("{id}")
    @ApiOperation(value = "根据ID查询信息")
    public ResultUtils getCtFile(@Valid @PathVariable Long id){
        SysUser entity = service.getBaseMapper().selectById(id);
        return ResultUtils.build(entity);
    }

    @GetMapping("/list")
    @ApiOperation(value = "列表查询")
    public ResultUtils getList(@Valid SysUserQueryDTO queryDTO) {
        QueryWrapper<SysUser> queryWrapper =  new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        Page<SysUser> page = new Page<>(queryDTO.getPageIndex(),queryDTO.getPageSize());
        IPage<SysUser> iPage = service.getBaseMapper().selectPage(page,queryWrapper);
        return ResultUtils.build(PageUtils.converterToPagination(iPage));
    }

    @GetMapping("/status/list")
    @OperationLog(record = false)
    public  ResultUtils getList(){
        return ResultUtils.build(UserStatusEnums.getMap());
    }

}
