package com.jtang.feign.config;

import com.jtang.feign.properties.AuthProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义属性注入
 * @date 2020/7/4 14:04
 * @author LinJinTang
 */
@Slf4j
@Configuration
public class PropertiesConfiguration {

    /** feign内部调用属性注入*/
    @Bean
    @ConditionalOnMissingBean
    public AuthProperties authProperties(){
        log.info("Inject default inner feign properties");
        AuthProperties authProperties = new AuthProperties();
        authProperties.setClientId("XcWebApp");
        authProperties.setClientSecret("XcWebApp");
        authProperties.setCookieDomain("localhost");
        authProperties.setTokenValiditySeconds(1200);
        authProperties.setRedirectUri("http://localhost");
        authProperties.setCookieMaxAge(-1);
        return authProperties;
    }
}
