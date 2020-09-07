package com.jtang.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Mvc配置
 * @author lin512100
 * @date 2020/9/7
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${system.baseFilePath}")
    private String baseFilePath;

    @Value("${system.baseImagePath}")
    private String baseImagePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/file/**", "/image/**").addResourceLocations("file:" +  baseFilePath).addResourceLocations("file:" + baseImagePath);
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/static/**", "/templates/**").addResourceLocations("classpath:/static/",
                "classpath:/templates/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}