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


}
