package com.jtang.account.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jtang.base.utils.Pagination;
import com.jtang.common.model.account.entity.PlatformMenu;
import com.jtang.common.utils.ResultUtils;
import com.jtang.account.query.PlatformMenuQueryDTO;
import com.jtang.account.service.IPlatformMenuService;
import com.jtang.web.utils.PageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

/**
* 菜单表 前端控制器
* @author jtang
* @since 2020-06-30
* @version v1.0
*/
@Slf4j
@Api(tags = "菜单表模块")
@RestController
@AllArgsConstructor
@RequestMapping("/menu")
public class PlatformMenuController {

    @Autowired
    private IPlatformMenuService service;

    @PostMapping
    @ApiOperation(value = "菜单表添加")
    public ResultUtils add(@Valid @RequestBody PlatformMenu entity){
        entity.setCreateTime(LocalDateTime.now());
        entity.setUpdateTime(LocalDateTime.now());
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
    @ApiOperation(value = "修改菜单表信息")
    public ResultUtils modify(@Valid @RequestBody PlatformMenu entity){
        service.getBaseMapper().updateById(entity);
        return ResultUtils.success;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据ID查询菜单表信息")
    public ResultUtils detail(@Valid @PathVariable Long id){
        PlatformMenu entity = service.getBaseMapper().selectById(id);
        return ResultUtils.build(entity);
    }

    @GetMapping("/list")
    @ApiOperation(value = "菜单表列表查询")
    public ResultUtils list(@Valid PlatformMenuQueryDTO queryDTO) {
        return ResultUtils.build(service.getMenuList(queryDTO));

    }

    @GetMapping("/listById/{userId}")
    @ApiOperation(value = "根据角色ID查询菜单列表")
    public ResultUtils getListByRole(@PathVariable("userId") Long userId){

        return ResultUtils.build(service.getListByRole(userId));
    }

}
