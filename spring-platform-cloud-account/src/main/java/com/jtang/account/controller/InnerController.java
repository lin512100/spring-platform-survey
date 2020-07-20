package com.jtang.account.controller;

import com.jtang.account.service.InnerService;
import com.jtang.common.model.account.response.HandleAllow;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 账户服务内部接口
 * @author linjt
 * @date 2020/7/20
 */
@Api(tags = "账户服务内部接口")
@RestController
@AllArgsConstructor
@RequestMapping("/inner")
public class InnerController {

    @Autowired
    private InnerService innerService;

    @GetMapping("/{id}")
    @ApiOperation(value = "根据角色ID查询角色操作功能")
    Map<String,List<HandleAllow>> getHandleAllow(){

    }
}
