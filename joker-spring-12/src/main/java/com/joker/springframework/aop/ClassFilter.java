package com.joker.springframework.aop;

/**
 * <p>
 * Filter that restricts matching of a pointcut or introduction to
 * a given set of target classes.
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/10/3
 */
public interface ClassFilter {

    /**
     * Should the pointcut apply to the given interface or target class?
     *
     * @param clazz the candidate target class
     * @return whether the advice should apply to the given target class
     */
    boolean matches(Class<?> clazz);

}
