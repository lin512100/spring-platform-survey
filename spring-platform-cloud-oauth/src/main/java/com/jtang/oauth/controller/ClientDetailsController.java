package com.jtang.oauth.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jtang.common.model.account.entity.OauthClientDetails;
import com.jtang.common.utils.ResultUtils;
import com.jtang.oauth.query.ClientDetailsQueryDTO;
import com.jtang.oauth.service.IClientDetailsService;
import com.jtang.web.utils.PageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.RestController;

/**
* 客户端信息 前端控制器
* @author jtang
* @since 2020-07-01
* @version v1.0
*/
@Api(tags = "客户端信息模块")
@RestController
@AllArgsConstructor
@RequestMapping("/client-details")
public class ClientDetailsController {

    @Autowired
    private IClientDetailsService service;

    @PostMapping
    @ApiOperation(value = "客户端信息添加")
    public ResultUtils addCtFile(@Valid @RequestBody OauthClientDetails entity){
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
    @ApiOperation(value = "修改客户端信息信息")
    public ResultUtils modifyClientDetails(@Valid @RequestBody OauthClientDetails entity){
        service.getBaseMapper().updateById(entity);
        return ResultUtils.success;
    }

    @GetMapping("{id}")
    @ApiOperation(value = "根据ID查询客户端信息信息")
    public ResultUtils getCtFile(@Valid @PathVariable Long id){
        OauthClientDetails entity = service.getBaseMapper().selectById(id);
        return ResultUtils.build(entity);
    }

    @GetMapping("/list")
    @ApiOperation(value = "客户端信息列表查询")
    public ResultUtils getList(@Valid ClientDetailsQueryDTO queryDTO) {
        QueryWrapper<OauthClientDetails> queryWrapper =  new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        Page<OauthClientDetails> page = new Page<>(queryDTO.getPageNum(),queryDTO.getPageSize());
        IPage<OauthClientDetails> iPage = service.getBaseMapper().selectPage(page,queryWrapper);
        return ResultUtils.build(PageUtils.converterToPagination(iPage));
    }

}
