package com.jtang.account.controller;

import com.jtang.account.service.IPlatformMenuService;
import com.jtang.account.service.IPlatformUserService;
import com.jtang.account.service.InnerService;
import com.jtang.base.client.InnerUrlConstants;
import com.jtang.common.annotation.OperationLog;
import com.jtang.common.model.account.response.HandleAllow;
import com.jtang.base.utils.ResultUtils;
import com.jtang.feign.model.UserDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 账户服务内部接口
 * @author lin512100
 * @date 2020/7/20
 */
@Api(value = "内部账户服务接口")
@RestController
@AllArgsConstructor
@OperationLog
@RequestMapping(InnerUrlConstants.INNER_PRE)
public class InnerController {

    @Autowired
    private InnerService innerService;

    @Autowired
    private IPlatformUserService userService;

    @Autowired
    private IPlatformMenuService menuService;

    @GetMapping(InnerUrlConstants.ALLOW_HANDLE)
    @ApiOperation(value = "根据角色ID查询角色操作功能")
    Map<String,List<HandleAllow>> getHandleAllow(@RequestParam("roleIds") String roleIds){
        if(StringUtils.isEmpty(roleIds)){
            return new HashMap<>(1);
        }
        List<Long> roleId = Arrays.stream(roleIds.split(",")).map(Long::parseLong).collect(Collectors.toList());
        return innerService.getHandleAllow(roleId);
    }

    @GetMapping(InnerUrlConstants.USER_INFO)
    public UserDao getUserInfo(@RequestParam("username") String username){
        return userService.loadUserByUsername(username);
    }

    @PostMapping(InnerUrlConstants.ASYNC_OPERATE_URL)
    public ResultUtils asyncOperateUrl(@RequestBody List<HashMap<String, String>> mapList,@RequestParam("server") String server){
        return ResultUtils.build(menuService.asyncOperateUrl(mapList,server));
    }
}
