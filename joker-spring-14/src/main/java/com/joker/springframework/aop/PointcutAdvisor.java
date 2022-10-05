package com.joker.springframework.aop;

/**
 * <p>
 * Superinterface for all Advisors that are driven by a pointcut.
 * This covers nearly all advisors except introduction advisors,
 * for which method-level matching doesn't apply.
 * </p>
 * Advisor 承担了 Pointcut 和 Advice 的组合，
 * Pointcut 用于获取 JoinPoint, 而 Advice 决定了 JoinPoint 执行什么操作
 *
 * @author jokerzzccc
 * @date 2022/10/4
 */
public interface PointcutAdvisor extends Advisor {

    /**
     * Get the Pointcut that drives this advisor.
     */
    Pointcut getPointcut();

}
