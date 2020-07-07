package com.jtang.account.controller;

import com.jtang.base.enums.UserStatusEnums;
import com.jtang.common.model.account.entity.PlatformUser;
import com.jtang.common.model.account.response.PlatformUserDTO;
import com.jtang.common.utils.ResultUtils;
import com.jtang.feign.model.UserDao;
import com.jtang.account.query.PlatformUserQueryDTO;
import com.jtang.account.service.IPlatformUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    public ResultUtils addCtFile(@Valid PlatformUserDTO entity){
        service.addUserInfo(entity);
        return ResultUtils.success;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据ID删除信息")
    public ResultUtils addCtFile(@Valid @PathVariable String id){
        String[] ids = id.split(",");
        List<Long> collect = Arrays.stream(ids).map(Long::parseLong).collect(Collectors.toList());
        // 删除用户信息
        service.getBaseMapper().deleteBatchIds(collect);
        // 删除权限信息

        return ResultUtils.success;
    }

    @PutMapping
    @ApiOperation(value = "修改用户信息")
    public ResultUtils modifyPlatformUser(@Valid PlatformUserDTO entity){
        service.updateUserInfo(entity);
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

    @GetMapping("/status/list")
    public  ResultUtils getList(){
        return ResultUtils.build(UserStatusEnums.getMap());
    }

}
