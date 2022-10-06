package com.joker.springframework.beans.factory.config;

import cn.hutool.core.bean.BeanException;
import com.joker.springframework.beans.factory.ConfigurableListableBeanFactory;

/**
 * <p>
 * 允许自定义修改 beanDefinition 属性信息
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/9/21
 */
public interface BeanFactoryPostProcessor {

    /**
     * 在所有的 BeanDefinition 加载完成后，实例化 Bean 对象之前，提供修改 BeanDefinition 属性的机制
     * @param beanFactory
     * @throws BeanException
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeanException;
}
