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
public class SpouseAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("关怀小两口(切面)：" + method);
    }

}
