package com.jtang.resource.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jtang.business.query.TbResourceInfoQueryDTO;
import com.jtang.resource.entity.TbResourceInfo;
import com.jtang.resource.service.ITbResourceInfoService;
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
* 资源信息 前端控制器
* @author jtang
* @since 2020-09-07
* @version v1.0
*/
@Api(tags = "资源信息模块")
@RestController
@AllArgsConstructor
@RequestMapping("/resource/tb-resource-info")
public class TbResourceInfoController {

    @Autowired
    private ITbResourceInfoService service;

    @PostMapping
    @ApiOperation(value = "资源信息添加")
    public ResultUtils addCtFile(@Valid @RequestBody TbResourceInfo entity){
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
    @ApiOperation(value = "修改资源信息信息")
    public ResultUtils modifyTbResourceInfo(@Valid @RequestBody TbResourceInfo entity){
        service.getBaseMapper().updateById(entity);
        return ResultUtils.success;
    }

    @GetMapping("{id}")
    @ApiOperation(value = "根据ID查询资源信息信息")
    public ResultUtils getCtFile(@Valid @PathVariable Long id){
        TbResourceInfo entity = service.getBaseMapper().selectById(id);
        return ResultUtils.build(entity);
    }

    @GetMapping("/list")
    @ApiOperation(value = "资源信息列表查询")
    public ResultUtils getList(@Valid TbResourceInfoQueryDTO queryDTO) {
        QueryWrapper<TbResourceInfo> queryWrapper =  new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        Page<TbResourceInfo> page = new Page<>(queryDTO.getPageIndex(),queryDTO.getPageSize());
        IPage<TbResourceInfo> iPage = service.getBaseMapper().selectPage(page,queryWrapper);
        return ResultUtils.build(PageUtils.converterToPagination(iPage));
    }

}
