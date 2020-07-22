package com.jtang.account.schedule;

import com.jtang.common.service.InitUrlService;
import com.jtang.common.utils.ResultUtils;
import com.jtang.feign.service.UserApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 同步操作功能列表
 * @date 2020/7/22 20:32
 * @author LinJinTang
 */
@Slf4j
@Configuration
public class AsyncOperator {

    @Autowired
    private UserApiService userApiService;

    @Scheduled(cron = "0/10 * * * * ?") //每50秒执行一次
    public void scheduledTaskByCorn() {
        log.info("开始同步操作功能列表");
        ResultUtils resultUtils = userApiService.asyncOperateUrl(InitUrlService.urlList,"account");
        System.out.println(resultUtils.getData());
        log.info("结束同步操作功能列表");
    }
}
