package com.joker.springframework.aop;

/**
 * <p>
 * 切入点接口：切点表达式，
 * </p>
 * Core Spring pointcut abstraction.
 *
 * <p>A pointcut is composed of a {@link ClassFilter} and a {@link MethodMatcher}.
 * Both these basic terms and a Pointcut itself can be combined to build up combinations
 * <p>
 *
 * @author jokerzzccc
 * @date 2022/10/3
 */
public interface Pointcut {

    /**
     * Return the ClassFilter for this pointcut.
     * @return the ClassFilter (never {@code null})
     */
    ClassFilter getClassFilter();

    /**
     * Return the MethodMatcher for this pointcut.
     * @return the MethodMatcher (never {@code null})
     */
    MethodMatcher getMethodMatcher();

}
