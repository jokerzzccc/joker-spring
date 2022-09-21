package com.joker.springframework.beans.factory.support;

import com.joker.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/9/18
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

   private final Map<String, Object> singletonObjects =  new HashMap<>();

    @Override
    public Object getSingleton(String name) {
        return singletonObjects.get(name);
    }

    protected void addSingleton(String beanName, Object singletonObject){
        singletonObjects.put(beanName, singletonObject);
    }

}
