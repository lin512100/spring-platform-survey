package com.jtang.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jtang.system.entity.Task;
import com.jtang.system.service.TaskService;
import com.jtang.system.query.TaskQueryDTO;
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
* 定时任务 前端控制器
* @author jtang
* @since 2020-09-28
* @version v1.0
*/
@Api(tags = "定时任务模块")
@RestController
@AllArgsConstructor
@RequestMapping("/task/tb-task")
public class TbTaskController {

    @Autowired
    private TaskService service;

    @PostMapping
    @ApiOperation(value = "定时任务添加")
    public ResultUtils addCtFile(@Valid @RequestBody Task entity){
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
    @ApiOperation(value = "修改定时任务信息")
    public ResultUtils modifyTbTask(@Valid @RequestBody Task entity){
        service.getBaseMapper().updateById(entity);
        return ResultUtils.success;
    }

    @GetMapping("{id}")
    @ApiOperation(value = "根据ID查询定时任务信息")
    public ResultUtils getCtFile(@Valid @PathVariable Long id){
        Task entity = service.getBaseMapper().selectById(id);
        return ResultUtils.build(entity);
    }

    @GetMapping("/list")
    @ApiOperation(value = "定时任务列表查询")
    public ResultUtils getList(@Valid TaskQueryDTO queryDTO) {
        QueryWrapper<Task> queryWrapper =  new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        Page<Task> page = new Page<>(queryDTO.getPageIndex(),queryDTO.getPageSize());
        IPage<Task> iPage = service.getBaseMapper().selectPage(page,queryWrapper);
        return ResultUtils.build(PageUtils.converterToPagination(iPage));
    }

}
