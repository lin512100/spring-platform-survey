package com.jtang.make.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jtang.business.query.TbResourceTagQueryDTO;
import com.jtang.make.entity.TbResourceTag;
import com.jtang.make.service.ITbResourceTagService;
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
* 资源标签 前端控制器
* @author jtang
* @since 2020-09-03
* @version v1.0
*/
@Api(tags = "资源标签模块")
@RestController
@AllArgsConstructor
@RequestMapping("/make/tag")
public class TbResourceTagController {

    @Autowired
    private ITbResourceTagService service;

    @PostMapping
    @ApiOperation(value = "资源标签添加")
    public ResultUtils addCtFile(@Valid @RequestBody TbResourceTag entity){
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
    @ApiOperation(value = "修改资源标签信息")
    public ResultUtils modifyTbResourceTag(@Valid @RequestBody TbResourceTag entity){
        service.getBaseMapper().updateById(entity);
        return ResultUtils.success;
    }

    @GetMapping("{id}")
    @ApiOperation(value = "根据ID查询资源标签信息")
    public ResultUtils getCtFile(@Valid @PathVariable Long id){
        TbResourceTag entity = service.getBaseMapper().selectById(id);
        return ResultUtils.build(entity);
    }

    @GetMapping("/list")
    @ApiOperation(value = "资源标签列表查询")
    public ResultUtils getList(@Valid TbResourceTagQueryDTO queryDTO) {
        QueryWrapper<TbResourceTag> queryWrapper =  new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        Page<TbResourceTag> page = new Page<>(queryDTO.getPageIndex(),queryDTO.getPageSize());
        IPage<TbResourceTag> iPage = service.getBaseMapper().selectPage(page,queryWrapper);
        return ResultUtils.build(PageUtils.converterToPagination(iPage));
    }

}
