package com.jtang.common.aspect;

import com.alibaba.fastjson.JSONObject;
import com.jtang.base.enums.ResultStatusEnums;
import com.jtang.base.utils.ResultUtils;
import com.jtang.common.annotation.OperationLog;
import com.jtang.common.model.common.SysLog;
import com.jtang.common.service.LogService;
import com.jtang.common.utils.HttpUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;


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

    @Autowired
    private LogService logService;

    /**
     * within(com.jtang.common.annotation.OperationLog)
     * 表示拦截含有这个注解的类中所有方法
     * annotation(com.jtang.common.annotation.OperationLog)
     * 表示拦截含有这个注解的方法
     */
    @Pointcut(value = "@within(com.jtang.common.annotation.OperationLog) || @annotation(com.jtang.common.annotation.OperationLog)")
    public void operationLog() {
    }

    @Around(value = "operationLog()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint){
        // 获取代理的日志信息
        SysLog sysLog = getSysLogInfo(proceedingJoinPoint);
        // 操作结果
        Object proceed;
        try{
            proceed = proceedingJoinPoint.proceed();
            addLog(sysLog, proceed);
            return proceed;
        }catch (Throwable throwable){
            return new ResultUtils<Object>().error(throwable.getMessage());
        }
    }

    /**
     * 切入信息
     * 日志写入
     * */
    private void addLog(SysLog sysLog, Object object){
        // 设置操作结束时间
        sysLog.setEndDate(LocalDateTime.now());

        // 尝试解析统一返回的结果
        try{
            ResultUtils resultUtils = JSONObject.parseObject(object.toString(), ResultUtils.class);
            sysLog.setSuccess(resultUtils.getCode());
            // 如果失败写入失败原因
            if(resultUtils.getCode() == ResultStatusEnums.FAIL.getCode() ){
                sysLog.setError(resultUtils.getMsg());
            }
        }catch (Exception error){
            sysLog.setError(error.getMessage());
        }
        // 调用日志写入接口
        logService.insert(sysLog);

    }

    /** 获取日志信息 */
    private SysLog getSysLogInfo(ProceedingJoinPoint joinPoint){
        SysLog sysLog = new SysLog();
        sysLog.setStartDate(LocalDateTime.now());
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        // 功能描述
        ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
        if(apiOperation != null){
            sysLog.setFunction(apiOperation.value());
        }

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        // 请求IP地址
        sysLog.setIp(HttpUtils.getIpAddr(request));
        // 请求的方法
        sysLog.setMethod(request.getMethod());
        // 请求链接
        sysLog.setUrl(request.getServletPath());


        // 服务名
        String serverName = env.getProperty("spring.application.name");
        if(!StringUtils.isEmpty(serverName)){
            sysLog.setService(serverName.toUpperCase());
        }

        OperationLog operationLog = method.getAnnotation(OperationLog.class);
        // 模快名 如果注解用在类上，则为null
        if(operationLog != null){
            if(!StringUtils.isNotBlank(operationLog.module())){
                Api api = joinPoint.getTarget().getClass().getAnnotation(Api.class);
                if(api != null){
                    sysLog.setModule(api.value());
                }
            }else {
                sysLog.setModule(operationLog.module());
            }
        }
        return sysLog;
    }

}
