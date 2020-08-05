package com.jtang.common.aspect;

import com.jtang.common.annotation.OperationLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Slf4j
@Aspect
public class OperationAspect {

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
        Class<?> aClass = joinPoint.getTarget().getClass();
        System.out.println(aClass.toString());
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

    @AfterReturning(pointcut = "operationLog()", returning = "ret")
    public void afterRet(Object ret) {
        log.info("出参数结果" + ret);
    }

    /**
     * 是否存在注解，如果存在就获取
     */
    private static OperationLog getAnnotationLog(JoinPoint joinPoint) throws Exception {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method != null) {
            return method.getAnnotation(OperationLog.class);
        }
        return null;
    }
}
