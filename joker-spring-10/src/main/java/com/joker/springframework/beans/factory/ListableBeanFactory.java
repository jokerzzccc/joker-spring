package com.joker.springframework.beans.factory;

import com.joker.springframework.beans.BeansException;

import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/9/21
 */
public interface ListableBeanFactory extends BeanFactory {

    /**
     * 按照类型返回 bena 实例
     * @param beanType
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T> Map<String, T> getBeansOfType(Class<T> beanType) throws BeansException;

    /**
     * 返回注册表中的所有 bean 名称
     * @return
     */
    String[] getBeanDefinitionNames();
}
