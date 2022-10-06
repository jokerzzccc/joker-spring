package com.joker.springframework.test.bean;

import com.joker.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * <p>
 *
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/10/6
 */
public class UserServiceBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("beforeAdvice 拦截方法：" + method.getName());
    }

}
