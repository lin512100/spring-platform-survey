package com.jtang.bussiness.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jtang.business.query.MenuQueryDTO;
import com.jtang.bussiness.entity.Menu;
import com.jtang.bussiness.service.IMenuService;
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
* 菜单 前端控制器
* @author jtang
* @since 2020-06-02
* @version v1.0
*/
@Api(tags = "菜单模块")
@RestController
@AllArgsConstructor
@RequestMapping("/bussiness/menu")
public class MenuController {

    @Autowired
    private IMenuService service;

    @PostMapping
    @ApiOperation(value = "菜单添加")
    public ResultUtils addCtFile(@Valid @RequestBody Menu entity){
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
    @ApiOperation(value = "修改菜单信息")
    public ResultUtils modifyMenu(@Valid @RequestBody Menu entity){
        service.getBaseMapper().updateById(entity);
        return ResultUtils.success;
    }

    @GetMapping("{id}")
    @ApiOperation(value = "根据ID查询菜单信息")
    public ResultUtils getCtFile(@Valid @PathVariable Long id){
        Menu entity = service.getBaseMapper().selectById(id);
        return ResultUtils.build(entity);
    }

    @GetMapping("/list")
    @ApiOperation(value = "菜单列表查询")
    public ResultUtils getList(@Valid MenuQueryDTO queryDTO) {
        QueryWrapper<Menu> queryWrapper =  new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        Page<Menu> page = new Page<>(queryDTO.getPageNum(),queryDTO.getPageSize());
        IPage<Menu> iPage = service.getBaseMapper().selectPage(page,queryWrapper);
        return ResultUtils.build(PageUtils.converterToPagination(iPage));
    }

}
