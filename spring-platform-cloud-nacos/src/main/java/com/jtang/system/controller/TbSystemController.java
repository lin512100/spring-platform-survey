package com.jtang.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jtang.business.query.TbSystemQueryDTO;
import com.jtang.system.entity.TbSystem;
import com.jtang.system.service.ITbSystemService;
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
* @since 2020-09-24
* @version v1.0
*/
@Api(tags = "模块")
@RestController
@AllArgsConstructor
@RequestMapping("/system/tb-system")
public class TbSystemController {

    @Autowired
    private ITbSystemService service;

    @PostMapping
    @ApiOperation(value = "添加")
    public ResultUtils addCtFile(@Valid @RequestBody TbSystem entity){
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
    public ResultUtils modifyTbSystem(@Valid @RequestBody TbSystem entity){
        service.getBaseMapper().updateById(entity);
        return ResultUtils.success;
    }

    @GetMapping("{id}")
    @ApiOperation(value = "根据ID查询信息")
    public ResultUtils getCtFile(@Valid @PathVariable Long id){
        TbSystem entity = service.getBaseMapper().selectById(id);
        return ResultUtils.build(entity);
    }

    @GetMapping("/list")
    @ApiOperation(value = "列表查询")
    public ResultUtils getList(@Valid TbSystemQueryDTO queryDTO) {
        QueryWrapper<TbSystem> queryWrapper =  new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        Page<TbSystem> page = new Page<>(queryDTO.getPageIndex(),queryDTO.getPageSize());
        IPage<TbSystem> iPage = service.getBaseMapper().selectPage(page,queryWrapper);
        return ResultUtils.build(PageUtils.converterToPagination(iPage));
    }

}
