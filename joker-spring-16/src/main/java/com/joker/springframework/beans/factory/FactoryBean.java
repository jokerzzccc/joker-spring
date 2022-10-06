package com.joker.springframework.beans.factory;

import cn.hutool.core.bean.BeanException;

/**
 * <p>
 * Interface to be implemented by objects used within a {@link BeanFactory}
 * which are themselves factories. If a bean implements this interface,
 * it is used as a factory for an object to expose, not directly as a bean
 * instance that will be exposed itself.
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/9/24
 */
public interface FactoryBean<T> {

    T getObject() throws Exception;

    Class<?> getObjectType();

    boolean isSingleton();

}
