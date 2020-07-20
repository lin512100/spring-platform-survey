package com.jtang.account.service.impl;

import com.jtang.account.service.IPlatformRoleMenuService;
import com.jtang.account.service.InnerService;
import com.jtang.common.model.account.response.HandleAllow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author linjt
 * @date 2020/7/20
 */
@Service
public class InnerServiceImpl implements InnerService {

    @Autowired
    private IPlatformRoleMenuService iPlatformRoleMenuService;

    @Override
    public Map<String, List<HandleAllow>> getHandleAllow(List<Long> roleIds) {
        List<HandleAllow> handleAllow = iPlatformRoleMenuService.getHandleAllow(roleIds);
        return handleAllow.stream().collect(Collectors.groupingBy(HandleAllow::getServer));
    }
}
