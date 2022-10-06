package com.joker.springframework.context.annotation;

import java.lang.annotation.*;

/**
 * <p>
 * 用于配置作用域的自定义注解
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/10/5
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Scope {

    String value() default "singleton";
}
