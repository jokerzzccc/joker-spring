package com.joker.springframework.beans.factory.config;

import com.joker.springframework.beans.BeansException;

/**
 * <p>
 * 提供了修改新实例化 Bean 对象的扩展点
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/9/21
 */
public interface BeanPostProcessor {

    /**
     * 在 Bean 对象执行初始化方法之前，执行此方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInstantiation(Object bean, String beanName)throws BeansException;

    /**
     * 在 Bean 对象执行初始化方法之后，执行此方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessAfterInstantiation(Object bean, String beanName)throws BeansException;
}
