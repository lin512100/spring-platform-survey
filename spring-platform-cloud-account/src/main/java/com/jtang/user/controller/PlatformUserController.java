package com.jtang.user.controller;

import com.jtang.common.model.account.entity.PlatformUser;
import com.jtang.common.utils.ResultUtils;
import com.jtang.feign.model.UserDao;
import com.jtang.user.query.PlatformUserQueryDTO;
import com.jtang.user.service.IPlatformUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
* 用户 前端控制器
* @author jtang
* @since 2020-06-30
* @version v1.0
*/
@Api(tags = "用户模块")
@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class PlatformUserController {

    @Autowired
    private IPlatformUserService service;

    @PostMapping
    @ApiOperation(value = "用户添加")
    public ResultUtils addCtFile(@Valid @RequestBody PlatformUser entity){
        service.save(entity);
        return ResultUtils.success;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据ID删除信息")
    public ResultUtils addCtFile(@Valid @PathVariable Long id){
        service.getBaseMapper().deleteById(id);
        return ResultUtils.success;
    }

    @PutMapping
    @ApiOperation(value = "修改用户信息")
    public ResultUtils modifyPlatformUser(@Valid @RequestBody PlatformUser entity){
        service.getBaseMapper().updateById(entity);
        return ResultUtils.success;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据ID查询用户信息")
    public ResultUtils getCtFile(@Valid @PathVariable Long id){
        PlatformUser entity = service.getBaseMapper().selectById(id);
        return ResultUtils.build(entity);
    }

    @GetMapping("/list")
    @ApiOperation(value = "用户列表查询")
    public ResultUtils getList(@Valid PlatformUserQueryDTO queryDTO) {
        return ResultUtils.build(service.getUserInfoList(queryDTO));
    }

    @GetMapping("/info")
    public UserDao getUserInfo(@RequestParam("username") String username){
        return service.loadUserByUsername(username);
    }

}
