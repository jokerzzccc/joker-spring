package com.joker.springframework.beans.factory.support;

import com.joker.springframework.beans.BeansException;
import com.joker.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * <p>
 * 通过 JDK 的方式实例化
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/9/19
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy{

    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException {
         Class clazz = beanDefinition.getBeanClass();
         try {
             if (null != ctor){
                 return clazz.getDeclaredConstructor(ctor.getParameterTypes()).newInstance(args);
             } else {
                 return clazz.getDeclaredConstructor().newInstance();
             }
         } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e){
             throw new BeansException("Failed to instantiate" + clazz.getName() + "]" + e);
         }
    }

}
