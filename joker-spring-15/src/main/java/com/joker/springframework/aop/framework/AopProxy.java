package com.joker.springframework.aop.framework;

/**
 * Delegate interface for a configured AOP proxy, allowing for the creation
 * of actual proxy objects.
 * <p>
 * AOP 代理的抽象接口：定义一个标准接口，用于获取代理类。
 * 具体实现代理的方式，有 JDK 和 Cglib 方式。
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/10/3
 */
public interface AopProxy {

    /**
     * Create a new proxy object.
     * <p>Uses the AopProxy's default class loader (if necessary for proxy creation):
     * usually, the thread context class loader.
     *
     * @return the new proxy object (never {@code null})
     * @see Thread#getContextClassLoader()
     */
    Object getProxy();

}
