package com.jtang.common.annotation;

import java.lang.annotation.*;

/**
 * @author linjt
 * @date 2020/8/4
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OperationLog {

    /** 模快名:默认为类注解@Api的值 */
    String module() default "";

    /** 记录日志 是：true 否：false */
    boolean record() default true;

    /** 敏感字段信息 */
    String[] sensitive() default {};


}
