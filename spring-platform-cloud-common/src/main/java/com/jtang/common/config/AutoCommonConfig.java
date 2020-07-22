package com.jtang.common.config;

import com.jtang.common.service.InitUrlService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * COMMON 公共配置
 * @date 2020/7/21 23:11
 * @author LinJinTang
 */
@Configuration
public class AutoCommonConfig {

    /** 反向加载URL信息工具类 */
    @Bean
    public InitUrlService initUrlService(){
        return new InitUrlService();
    };

}
