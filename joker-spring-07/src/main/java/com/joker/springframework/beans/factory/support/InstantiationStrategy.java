package com.joker.springframework.beans.factory.support;

import com.joker.springframework.beans.BeansException;
import com.joker.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * <p>
 * 实例化策略接口
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/9/19
 */
public interface InstantiationStrategy {

    /**
     * 实例化接口
     * @param beanDefinition
     * @param beanName
     * @param ctor 包含了类的必要信息，为了拿到符合入参信息对应的构造函数
     * @param args 入参信息
     * @return
     * @throws BeansException
     */
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException;

}
