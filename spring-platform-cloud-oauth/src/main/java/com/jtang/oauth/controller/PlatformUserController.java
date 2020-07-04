package com.jtang.oauth.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jtang.common.model.oauth.entity.PlatformUser;
import com.jtang.common.utils.ResultUtils;
import com.jtang.oauth.query.PlatformUserQueryDTO;
import com.jtang.oauth.service.IPlatformUserService;
import com.jtang.web.utils.PageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.RestController;

/**
* 用户 前端控制器
* @author jtang
* @since 2020-06-30
* @version v1.0
*/
@Api(tags = "用户模块")
@RestController
@AllArgsConstructor
@RequestMapping("/oauth/platform-user")
public class PlatformUserController {

    @Autowired
    private IPlatformUserService service;

    @PostMapping
    @ApiOperation(value = "用户添加")
    public ResultUtils addCtFile(@Valid @RequestBody PlatformUser entity){
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
    @ApiOperation(value = "修改用户信息")
    public ResultUtils modifyPlatformUser(@Valid @RequestBody PlatformUser entity){
        service.getBaseMapper().updateById(entity);
        return ResultUtils.success;
    }

    @GetMapping("{id}")
    @ApiOperation(value = "根据ID查询用户信息")
    public ResultUtils getCtFile(@Valid @PathVariable Long id){
        PlatformUser entity = service.getBaseMapper().selectById(id);
        return ResultUtils.build(entity);
    }

    @GetMapping("/list")
    @ApiOperation(value = "用户列表查询")
    public ResultUtils getList(@Valid PlatformUserQueryDTO queryDTO) {
        QueryWrapper<PlatformUser> queryWrapper =  new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        Page<PlatformUser> page = new Page<>(queryDTO.getPageNum(),queryDTO.getPageSize());
        IPage<PlatformUser> iPage = service.getBaseMapper().selectPage(page,queryWrapper);
        return ResultUtils.build(PageUtils.converterToPagination(iPage));
    }

}
