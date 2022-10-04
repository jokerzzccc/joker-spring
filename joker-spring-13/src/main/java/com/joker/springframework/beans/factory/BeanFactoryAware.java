package com.joker.springframework.beans.factory;

import com.joker.springframework.beans.BeansException;

import java.beans.Beans;

/**
 * <p>
 * 实现此接口，就能感知到所属的 BeanFactory
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/9/24
 */
public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
