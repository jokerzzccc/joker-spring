package com.joker.springframework.beans.factory.support;

import com.joker.springframework.beans.BeansException;
import com.joker.springframework.beans.factory.BeanFactory;
import com.joker.springframework.beans.factory.config.BeanDefinition;

/**
 * <p>
 * 抽象模板定义方法
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/9/18
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name, null);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) getBean(name);
    }

    protected <T> T doGetBean(final String name, final Object... args) {
        Object bean = getSingleton(name);
        if (bean != null) {
            return (T) bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return (T) createBean(name, beanDefinition, args);
    }

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args);

    protected abstract BeanDefinition getBeanDefinition(String name) throws BeansException;

}
