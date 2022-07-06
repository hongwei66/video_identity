package com.longma.videoidenty.config;

import java.lang.annotation.*;

/**
 * 定义系统日志注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLogAno {
    String value() default "";
}
