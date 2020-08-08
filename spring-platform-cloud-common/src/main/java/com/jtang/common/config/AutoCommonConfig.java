package com.jtang.common.config;

import com.jtang.common.aspect.OperationAspect;
import com.jtang.common.service.InitUrlService;
import com.jtang.common.service.LogService;
import com.jtang.common.service.impl.DefaultLogServciceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * COMMON 公共配置
 * @date 2020/7/21 23:11
 * @author LinJinTang
 */
@Slf4j
@Configuration
public class AutoCommonConfig {

    /** 反向加载URL信息工具类 */
    @Bean
    public InitUrlService initUrlService(){
        log.info("系统公共组件初始化 -> 反向加载URL信息");
        return new InitUrlService();
    };

    @Bean
    public OperationAspect operationLog(){
        log.info("系统公共组件初始化 -> 日志切面注入");
        return new OperationAspect();
    }

    /** 默认日志实现类 */
    @Bean
    @ConditionalOnMissingBean()
    public LogService logService(){
        log.info("系统公共组件初始化 -> 默认打印操作日志");
        return new DefaultLogServciceImpl();
    }

}
