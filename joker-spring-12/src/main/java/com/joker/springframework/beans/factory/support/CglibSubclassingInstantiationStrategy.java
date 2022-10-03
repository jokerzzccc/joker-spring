package com.joker.springframework.beans.factory.support;

import com.joker.springframework.beans.BeansException;
import com.joker.springframework.beans.factory.config.BeanDefinition;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

/**
 * <p>
 * 通过 Cglib 的方式实例化 bean
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/9/19
 */
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy{

    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException {
         Enhancer enhancer = new Enhancer();
         enhancer.setSuperclass(beanDefinition.getBeanClass());
         enhancer.setCallback(new NoOp() {
             @Override
             public int hashCode() {
                 return super.hashCode();
             }
         });
         if (null == ctor) {
             return enhancer.create();
         }
         return enhancer.create(ctor.getParameterTypes(), args);
    }

}
