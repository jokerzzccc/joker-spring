package com.joker.springframework.aop.framework;

import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/**
 * Spring's implementation of the AOP Alliance
 * {@link MethodInvocation} interface,
 *
 * <p>Invokes the target object using reflection. Subclasses can override the
 * {@link #invokeJoinpoint()} method to change this behavior, so this is also
 * a useful base class for more specialized MethodInvocation implementations.
 *
 * <p>
 * 入参的包装信息：提供了入参对象：目标对象，方法，入参
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/10/3
 */
public class ReflectiveMethodInvocation implements MethodInvocation {

    /**
     * 目标对象
     */
    protected final Object target;

    /**
     * 方法
     */
    protected final Method method;

    /**
     * 入参
     */
    protected final Object[] arguments;

    public ReflectiveMethodInvocation(Object target, Method method, Object[] arguments) {
        this.target = target;
        this.method = method;
        this.arguments = arguments;
    }

    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public Object[] getArguments() {
        return arguments;
    }

    @Override
    public Object proceed() throws Throwable {
        return method.invoke(target, arguments);
    }

    @Override
    public Object getThis() {
        return target;
    }

    @Override
    public AccessibleObject getStaticPart() {
        return method;
    }

}
