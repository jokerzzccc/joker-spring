package com.joker.springframework.beans.factory;

import com.joker.springframework.beans.BeansException;
import com.joker.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.joker.springframework.beans.factory.config.BeanDefinition;
import com.joker.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 * <p>
 *
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/9/21
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

}
