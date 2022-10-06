package com.joker.springframework.aop;

import com.joker.springframework.util.ClassUtils;

/**
 * A {@code TargetSource} is used to obtain the current "target" of
 * an AOP invocation, which will be invoked via reflection if no around
 * advice chooses to end the interceptor chain itself.
 * <p>
 * 被代理的目标对象
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/10/3
 */
public class TargetSource {

    private final Object target;

    public TargetSource(Object target) {
        this.target = target;
    }

    /**
     * Return the type of targets returned by this {@link TargetSource}.
     * <p>Can return {@code null}, although certain usages of a {@code TargetSource}
     * might just work with a predetermined target class.
     *
     * @return the type of targets returned by this {@link TargetSource}
     */
    public Class<?>[] getTargetClass() {
        Class<?> clazz = this.target.getClass();
        clazz = ClassUtils.isCglibProxyClass(clazz) ? clazz.getSuperclass() : clazz;
        return clazz.getInterfaces();
    }

    /**
     * Return a target instance. Invoked immediately before the
     * AOP framework calls the "target" of an AOP method invocation.
     *
     * @return the target object which contains the joinpoint,
     * or {@code null} if there is no actual target instance
     * @throws Exception if the target object can't be resolved
     */
    public Object getTarget() {
        return this.target;
    }

}
