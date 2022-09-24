package com.joker.springframework.beans.factory.config;

import com.joker.springframework.beans.PropertyValue;
import com.joker.springframework.beans.PropertyValues;
import com.joker.springframework.beans.factory.DisposableBean;
import com.joker.springframework.beans.factory.InitializingBean;

/**
 * <p>
 * 定义的 bean 实体
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/9/18
 */
public class BeanDefinition {

    private Class beanClass;

    private PropertyValues propertyValues;

    private String InitMethodName;

    private String destroyMethodName;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
        this.propertyValues = new PropertyValues();
    }

    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

    public String getInitMethodName() {
        return InitMethodName;
    }

    public void setInitMethodName(String initMethodName) {
        InitMethodName = initMethodName;
    }

    public String getDestroyMethodName() {
        return destroyMethodName;
    }

    public void setDestroyMethodName(String destroyMethodName) {
        this.destroyMethodName = destroyMethodName;
    }

}
