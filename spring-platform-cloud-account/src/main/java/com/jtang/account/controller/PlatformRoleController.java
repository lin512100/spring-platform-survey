package com.jtang.account.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jtang.base.response.ResponseResult;
import com.jtang.base.utils.Pagination;
import com.jtang.common.model.account.entity.PlatformRole;
import com.jtang.common.utils.ResultUtils;
import com.jtang.account.query.PlatformRoleQueryDTO;
import com.jtang.account.service.IPlatformRoleService;
import com.jtang.web.utils.PageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

/**
* 角色 前端控制器
* @author jtang
* @since 2020-06-30
* @version v1.0
*/
@Api(tags = "角色模块")
@RestController
@AllArgsConstructor
@RequestMapping("/role")
public class PlatformRoleController {

    @Autowired
    private IPlatformRoleService service;

    @PostMapping
    @ApiOperation(value = "角色添加")
    public ResultUtils add(@Valid @RequestBody PlatformRole entity){
        entity.setCreateTime(LocalDateTime.now());
        entity.setUpdateTime(LocalDateTime.now());
        service.save(entity);
        return ResultUtils.success;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据ID删除信息")
    public ResultUtils delete(@Valid @PathVariable Long id){
        // 查询是否有用户拥有此角色
        service.getBaseMapper().deleteById(id);
        return ResultUtils.success;
    }

    @PutMapping
    @ApiOperation(value = "修改角色信息")
    public ResultUtils modify(@Valid @RequestBody PlatformRole entity){
        service.getBaseMapper().updateById(entity);
        return ResultUtils.success;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据ID查询角色信息")
    public ResultUtils detail(@Valid @PathVariable Long id){
        PlatformRole entity = service.getBaseMapper().selectById(id);
        return ResultUtils.build(entity);
    }

    @GetMapping("/list")
    @ApiOperation(value = "角色列表查询")
    public ResultUtils list(@Valid PlatformRoleQueryDTO queryDTO) {
        QueryWrapper<PlatformRole> queryWrapper =  new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        if(queryDTO.getPageIndex() == null || queryDTO.getPageSize() == null){
            List<PlatformRole> platformRoleList = service.getBaseMapper().selectList(queryWrapper);
            return ResultUtils.build(new Pagination<>((platformRoleList == null)?0:platformRoleList.size(),platformRoleList));
        }
        Page<PlatformRole> page = new Page<>(queryDTO.getPageIndex(),queryDTO.getPageSize());
        IPage<PlatformRole> iPage = service.getBaseMapper().selectPage(page,queryWrapper);
        return ResultUtils.build(PageUtils.converterToPagination(iPage));
    }

    @GetMapping("/tree")
    public ResultUtils tree(){
    return ResultUtils.build(service.getMenuTree());
    }

}
