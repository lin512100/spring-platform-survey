package com.jtang.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Slf4j
@Aspect
public class OperationLog {

    /**
     * within(com.jtang.common.annotation.OperationLog)
     * 表示拦截含有这个注解的类中所有方法
     * @annotation(com.jtang.common.annotation.OperationLog)
     * 表示拦截含有这个注解的方法
     */
    @Pointcut(value = "@within(com.jtang.common.annotation.OperationLog) || @annotation(com.jtang.common.annotation.OperationLog)")
    public void operationLog() {
    }

    @Before(value = "operationLog()")
    public void doBefore(JoinPoint joinPoint){
        //获取请求request
        ServletRequestAttributes attributes = (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();

        //通过servletRequestAttributes获取request
        HttpServletRequest request = attributes.getRequest();
        //获取请求的相应参数
        StringBuffer url = request.getRequestURL();
        //写入请求的url
        log.info("URL: " + url.toString());
        String method = request.getMethod();
        log.info("切面调用成功");

    }

    @AfterReturning(pointcut = "operationLog()",returning = "ret")
    public void afterRet(Object ret){
        log.info("出参数结果" + ret);
    }
}
