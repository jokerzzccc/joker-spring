package com.joker.springframework.aop;

import java.lang.reflect.Method;

/**
 * <p>
 * Part of a {@link PointCut}: Checks whether the target method is eligible for advice.
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/10/3
 */
public interface MethodMatcher {

    /**
     * Perform static checking whether the given method matches. If this
     *
     * @return whether or not this method matches statically
     */
    boolean matches(Method method, Class<?> targetClass);

}
