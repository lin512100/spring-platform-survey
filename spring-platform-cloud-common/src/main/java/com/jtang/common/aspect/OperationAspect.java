package com.jtang.common.aspect;

import com.jtang.common.annotation.OperationLog;
import com.jtang.common.model.common.SysLog;
import com.jtang.common.utils.HttpUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;


/**
 * 切面实现类
 * @author linjt
 * @date 2020/8/7
 */
@Slf4j
@Aspect
public class OperationAspect {

    @Autowired
    private Environment env;

    /**
     * within(com.jtang.common.annotation.OperationLog)
     * 表示拦截含有这个注解的类中所有方法
     * annotation(com.jtang.common.annotation.OperationLog)
     * 表示拦截含有这个注解的方法
     */
    @Pointcut(value = "@within(com.jtang.common.annotation.OperationLog) || @annotation(com.jtang.common.annotation.OperationLog)")
    public void operationLog() {
    }

    @Before(value = "operationLog()")
    public void doBefore(JoinPoint joinPoint) {

        //获取请求request
        ServletRequestAttributes attributes = (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();

    }

    @AfterReturning(pointcut = "operationLog()", returning = "ret")
    public void afterRet(Object ret) {
        log.info("出参数结果" + ret);
    }

    /** 获取日志信息 */
    private SysLog getSysLog(JoinPoint joinPoint){
        SysLog sysLog = new SysLog();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        // 功能描述
        ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
        if(apiOperation != null){
            log.info(apiOperation.value());
        }

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        // 请求IP地址
        sysLog.setIp(HttpUtils.getIpAddr(request));
        // 请求的方法
        sysLog.setMethod(request.getMethod());

        OperationLog operationLog = method.getAnnotation(OperationLog.class);

        // 服务名
        String serverName = env.getProperty("spring.application.name");
        if(!StringUtils.isEmpty(serverName)){
            sysLog.setService(serverName.toUpperCase());
        }

        // 模快名
        if(!StringUtils.isNotBlank(operationLog.module())){

        }


        return sysLog;



    }


}
