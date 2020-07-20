package com.jtang.account.controller;

import com.jtang.account.service.IPlatformUserService;
import com.jtang.account.service.InnerService;
import com.jtang.base.client.InnerUrlConstants;
import com.jtang.common.model.account.response.HandleAllow;
import com.jtang.feign.model.UserDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 账户服务内部接口
 * @author linjt
 * @date 2020/7/20
 */
@Api(tags = "账户服务内部接口")
@RestController
@AllArgsConstructor
@RequestMapping(InnerUrlConstants.INNER_PRE)
public class InnerController {

    @Autowired
    private InnerService innerService;

    @Autowired
    private IPlatformUserService service;

    @GetMapping(InnerUrlConstants.ALLOW_HANDLE)
    @ApiOperation(value = "根据角色ID查询角色操作功能")
    Map<String,List<HandleAllow>> getHandleAllow(@RequestParam("roleIds") String roleIds){
        System.out.println("===================");
        if(StringUtils.isEmpty(roleIds)){
            return new HashMap<>(1);
        }
        List<Long> roleId = Arrays.stream(roleIds.split(",")).map(Long::parseLong).collect(Collectors.toList());
        return innerService.getHandleAllow(roleId);
    }

    @GetMapping(InnerUrlConstants.USER_INFO)
    public UserDao getUserInfo(@RequestParam("username") String username){
        return service.loadUserByUsername(username);
    }
}
