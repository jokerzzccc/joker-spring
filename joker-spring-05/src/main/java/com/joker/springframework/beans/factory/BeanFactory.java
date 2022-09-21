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
     * @param name
     * @return
     * @throws BeansException
     */
    Object getBean(String name) throws BeansException;

    /**
     * 考虑到有构造函数的 bean 实例化
     * @param name
     * @param args
     * @return
     * @throws BeansException
     */
    Object getBean(String name, Object... args) throws BeansException;

    /**
     * 通过 bean 类型，获取 bean
     * @param name
     * @param requiredType
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T> T getBean(String name, Class<T> requiredType) throws BeansException;

}
