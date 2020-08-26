package com.jtang.common.config;

import com.jtang.common.aspect.OperationAspect;
import com.jtang.common.service.InitUrlService;
import com.jtang.common.service.LogService;
import com.jtang.common.service.impl.DefaultLogServciceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * COMMON 公共配置
 * @date 2020/7/21 23:11
 * @author LinJinTang
 */
@Slf4j
@EnableAsync
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

    @Bean
    public Executor logExecutor() {
        // 设置核心线程数
        int corePoolSize = 5;
        // 设置最大线程数
        int maxPoolSize = 20;
        // 设置线程活跃时间（秒）
        int keepAliveSeconds = 300;
        // 设置队列容量
        int queueCapacity = 500;
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //核心线程池大小
        executor.setCorePoolSize(corePoolSize);
        //最大线程数
        executor.setMaxPoolSize(maxPoolSize);
        //队列容量
        executor.setQueueCapacity(keepAliveSeconds);
        //活跃时间
        executor.setKeepAliveSeconds(queueCapacity);
        //线程名字前缀
        executor.setThreadNamePrefix("LogExecutePool-");

        // setRejectedExecutionHandler：当pool已经达到max size的时候，如何处理新任务
        // CallerRunsPolicy：不在新线程中执行任务，而是由调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.initialize();
        return executor;
    }

}
