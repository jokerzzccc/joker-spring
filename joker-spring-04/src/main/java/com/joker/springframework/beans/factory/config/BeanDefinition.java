package com.joker.springframework.beans.factory.config;

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

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public  void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

}
