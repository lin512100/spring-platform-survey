package com.jtang.feign.service;

import com.jtang.base.client.InnerUrlConstants;
import com.jtang.common.utils.ResultUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;

/**
 * 初始化服务类
 * @author linjt
 * @date 2020/7/22
 */
public interface InitService {

    /** 同步URL接口信息到账户服务 */
    @PostMapping(InnerUrlConstants.ASYNC_OPERATE_URL)
    ResultUtils asyncOperateUrl(@RequestBody List<HashMap<String, String>> mapList);
}
