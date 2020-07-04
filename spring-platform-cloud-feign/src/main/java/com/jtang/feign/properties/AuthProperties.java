package com.jtang.feign.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @date 2020/7/2 21:25
 * @author LinJinTang
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthProperties {
    /** token存储到redis的过期时间 */
    private Integer tokenValiditySeconds;
    /** 客户端ID */
    private String clientId;
    /** 客户端密码 */
    private String clientSecret;
    /** cookie 域名 */
    private String cookieDomain;
    /** cookieMaxAge */
    private Integer cookieMaxAge;
    /** 重定向URL */
    private String redirectUri;
}
