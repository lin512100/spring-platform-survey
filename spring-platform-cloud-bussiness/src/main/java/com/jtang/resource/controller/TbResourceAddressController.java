package com.jtang.resource.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jtang.business.query.TbResourceAddressQueryDTO;
import com.jtang.resource.entity.TbResourceAddress;
import com.jtang.resource.service.ITbResourceAddressService;
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
* 资源地址 前端控制器
* @author jtang
* @since 2020-09-07
* @version v1.0
*/
@Api(tags = "资源地址模块")
@RestController
@AllArgsConstructor
@RequestMapping("/resource/tb-resource-address")
public class TbResourceAddressController {

    @Autowired
    private ITbResourceAddressService service;

    @PostMapping
    @ApiOperation(value = "资源地址添加")
    public ResultUtils addCtFile(@Valid @RequestBody TbResourceAddress entity){
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
    @ApiOperation(value = "修改资源地址信息")
    public ResultUtils modifyTbResourceAddress(@Valid @RequestBody TbResourceAddress entity){
        service.getBaseMapper().updateById(entity);
        return ResultUtils.success;
    }

    @GetMapping("{id}")
    @ApiOperation(value = "根据ID查询资源地址信息")
    public ResultUtils getCtFile(@Valid @PathVariable Long id){
        TbResourceAddress entity = service.getBaseMapper().selectById(id);
        return ResultUtils.build(entity);
    }

    @GetMapping("/list")
    @ApiOperation(value = "资源地址列表查询")
    public ResultUtils getList(@Valid TbResourceAddressQueryDTO queryDTO) {
        QueryWrapper<TbResourceAddress> queryWrapper =  new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        Page<TbResourceAddress> page = new Page<>(queryDTO.getPageIndex(),queryDTO.getPageSize());
        IPage<TbResourceAddress> iPage = service.getBaseMapper().selectPage(page,queryWrapper);
        return ResultUtils.build(PageUtils.converterToPagination(iPage));
    }

}
