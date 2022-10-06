package com.joker.springframework.beans.factory;

import com.joker.springframework.beans.BeansException;

/**
 * <p>
 * 构造 bean 的工厂
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/9/18
 */
public interface BeanFactory {

    /**
     * 无构造函数的 bean 实例
     *
     * @param name
     * @return
     * @throws BeansException
     */
    Object getBean(String name) throws BeansException;

    /**
     * 考虑到有构造函数的 bean 实例化
     *
     * @param name
     * @param args
     * @return
     * @throws BeansException
     */
    Object getBean(String name, Object... args) throws BeansException;

    /**
     * 通过 bean 类型，获取 bean
     *
     * @param name
     * @param requiredType
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T> T getBean(String name, Class<T> requiredType) throws BeansException;

    /**
     * Return the bean instance that uniquely matches the given object type, if any.
     * <p>This method goes into {@link ListableBeanFactory} by-type lookup territory
     * but may also be translated into a conventional by-name lookup based on the name
     * of the given type.
     *
     * 根据 Bean 类型返回一个唯一的 Bean 实例。
     *
     * @param requiredType
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T> T getBean(Class<T> requiredType) throws BeansException;

}
