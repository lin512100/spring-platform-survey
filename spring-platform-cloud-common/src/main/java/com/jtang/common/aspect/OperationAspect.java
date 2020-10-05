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
 * @author lin512100
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
        // 结果
        Object object;
        // 日志信息
        try{
            object = proceedingJoinPoint.proceed();
        }catch (Throwable throwable){
            throwable.printStackTrace();
            object =  ResultUtils.errorMsg(throwable.getMessage());
        }
        if(sysLog != null){
            addLog(sysLog, object);
        }
        return object;
    }

    /** 日志信息写入 */
    private void addLog(SysLog sysLog, Object object){
        // 设置操作结束时间
        sysLog.setEndDate(LocalDateTime.now());
        // 尝试解析统一返回的结果
        try{
            ResultUtils<Object> resultUtils = ResultUtils.getData(object.toString());
            // 写入执行的状态
            sysLog.setSuccess(resultUtils.getCode());
            // 如果失败写入失败原因
            if(resultUtils.getCode() == ResultStatusEnums.FAIL.getCode()){
                sysLog.setError(resultUtils.getMsg());
            }
        }catch (Exception error){
            error.printStackTrace();
            sysLog.setError(error.getMessage());
        }
        // 调用日志写入接口
        logService.insert(sysLog);

    }

    /** 获取日志信息 */
    private SysLog getSysLogInfo(ProceedingJoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        OperationLog operationLog = method.getAnnotation(OperationLog.class);

        // 是否记录日志
        if(operationLog != null && !operationLog.record()){
            return null;
        }

        // 敏感字段信息
        String[] sensitive = new String[]{};
        if(operationLog != null){
             sensitive = operationLog.sensitive();

        }

        SysLog sysLog = getSwaggerUiDefaultInfo(joinPoint);


        sysLog.setStartDate(LocalDateTime.now());

        // 设置请求参数
        String[] argNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        final Object[] argValues = joinPoint.getArgs();
        StringBuilder sb = new StringBuilder();


        for (int i = 0; i < argNames.length; i++) {
            if(argValues[i] == null){
                continue;
            }

            String value =  argValues[i].toString();
            // 如果请求参数含有敏感信息，则隐藏
            for(String senStr : sensitive){
                if (argNames[i].equals(senStr)) {
                    value = "******";
                    break;
                }
            }
            sb.append(argNames[i]).append("=").append(value).append(",");
        }
        String paramStr = sb.length() > 0 ? sb.toString().substring(0, sb.length() - 1) + "]" : "";
        sysLog.setParams(paramStr);

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


        // 模快名 如果注解用在类上，则为null
        if(operationLog != null && StringUtils.isNotBlank(operationLog.module())){
            sysLog.setModule(operationLog.module());
        }

        return sysLog;
    }

    /** 默认日志信息，来自于Swagger*/
    private SysLog getSwaggerUiDefaultInfo(ProceedingJoinPoint joinPoint){
        SysLog sysLog = new SysLog();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        // 功能描述
        ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
        if(apiOperation != null){
            sysLog.setFunction(apiOperation.value());
        }

        // 设置swagger默认的信息
        Api api = joinPoint.getTarget().getClass().getAnnotation(Api.class);
        if(api != null){
            // swagger 设置的是value描述值
            if(StringUtils.isNotBlank(api.value())){
                sysLog.setModule(api.value());
            }
            // swagger 设置的是tag描述值
            if(api.tags().length > 0){
                sysLog.setModule(StringUtils.join(api.tags(),","));
            }
        }
        return sysLog;
    }

}
