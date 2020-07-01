package com.jtang.oauth.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jtang.business.query.PlatformMenuQueryDTO;
import com.jtang.common.model.auth.PlatformMenu;
import com.jtang.oauth.service.IPlatformMenuService;
import com.jtang.web.utils.PageUtils;
import com.jtang.common.utils.ResultUtils;
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
* @since 2020-06-30
* @version v1.0
*/
@Api(tags = "菜单表模块")
@RestController
@AllArgsConstructor
@RequestMapping("/oauth/platform-menu")
public class PlatformMenuController {

    @Autowired
    private IPlatformMenuService service;

    @PostMapping
    @ApiOperation(value = "菜单表添加")
    public ResultUtils addCtFile(@Valid @RequestBody PlatformMenu entity){
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
    public ResultUtils modifyPlatformMenu(@Valid @RequestBody PlatformMenu entity){
        service.getBaseMapper().updateById(entity);
        return ResultUtils.success;
    }

    @GetMapping("{id}")
    @ApiOperation(value = "根据ID查询菜单表信息")
    public ResultUtils getCtFile(@Valid @PathVariable Long id){
        PlatformMenu entity = service.getBaseMapper().selectById(id);
        return ResultUtils.build(entity);
    }

    @GetMapping("/list")
    @ApiOperation(value = "菜单表列表查询")
    public ResultUtils getList(@Valid PlatformMenuQueryDTO queryDTO) {
        QueryWrapper<PlatformMenu> queryWrapper =  new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        Page<PlatformMenu> page = new Page<>(queryDTO.getPageNum(),queryDTO.getPageSize());
        IPage<PlatformMenu> iPage = service.getBaseMapper().selectPage(page,queryWrapper);
        return ResultUtils.build(PageUtils.converterToPagination(iPage));
    }

}
