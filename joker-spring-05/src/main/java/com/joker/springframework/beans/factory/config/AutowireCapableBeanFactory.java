package com.joker.springframework.beans.factory.config;

import com.joker.springframework.beans.factory.BeanFactory;

/**
 * <p>
 * Extension of the {@link com.joker.springframework.beans.factory.BeanFactory}
 * interface to be implemented by bean factories that are capable of
 * autowiring, provided that they want to expose this functionality for
 * existing bean instances.
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/9/21
 */
public interface AutowireCapableBeanFactory extends BeanFactory {

}
