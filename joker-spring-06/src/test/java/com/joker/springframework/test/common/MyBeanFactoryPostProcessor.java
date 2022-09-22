package com.joker.springframework.test.common;

import cn.hutool.core.bean.BeanException;
import com.joker.springframework.beans.PropertyValue;
import com.joker.springframework.beans.PropertyValues;
import com.joker.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.joker.springframework.beans.factory.config.BeanDefinition;
import com.joker.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.joker.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 * <p>
 *
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/9/22
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeanException {
        final BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        final PropertyValues propertyValues = beanDefinition.getPropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("company", "改为：成都"));
    }

}
