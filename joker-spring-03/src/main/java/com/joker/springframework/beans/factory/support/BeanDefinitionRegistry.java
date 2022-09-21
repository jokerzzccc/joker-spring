package com.joker.springframework.beans.factory.support;

import com.joker.springframework.beans.factory.config.BeanDefinition;

/**
 * <p>
 *
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/9/18
 */
public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

}
