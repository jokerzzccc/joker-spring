package com.joker.springframework.beans.factory;

import com.joker.springframework.beans.BeansException;

/**
 * <p>
 * Defines a factory which can return an Object instance
 * (possibly shared or independent) when invoked.
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/10/6
 */
public interface ObjectFactory<T> {

    /**
     * Return an instance (possibly shared or independent)
     * of the object managed by this factory.
     *
     * @return the resulting instance
     * @throws BeansException in case of creation errors
     */
    T getObject() throws BeansException;

}
