package com.joker.springframework.beans.factory.config;

/**
 * <p>
 * bean 的引用
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/9/20
 */
public class BeanReference {

    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }

}
