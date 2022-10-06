package com.joker.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanException;
import com.joker.springframework.beans.BeansException;
import com.joker.springframework.beans.factory.FactoryBean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * FactoryBeanRegistrySupport 类：主要处理的就是关于 FactoryBean 此类对象的注册操作
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/9/24
 */
public abstract class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry {

    /**
     * Cache of singleton objects created by FactoryBeans: FactoryBean name to object.
     */
    private final Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap<>();

    protected Object getCachedObjectForFactoryBean(String beanName) {
        return this.factoryBeanObjectCache.get(beanName);
    }

    protected Object getObjectFromFactoryBean(FactoryBean factory, String beanName) {
        if (factory.isSingleton()) {
            Object object = this.factoryBeanObjectCache.get(beanName);
            if (object == null) {
                object = doGetObjectFromFactoryBean(factory, beanName);
                this.factoryBeanObjectCache.put(beanName, object);
            }
            return object;
        } else {
            return doGetObjectFromFactoryBean(factory, beanName);
        }
    }

    protected Object doGetObjectFromFactoryBean(final FactoryBean factory, final String beanName) {
        try {
            return factory.getObject();
        } catch (BeanException e) {
            throw new BeansException("FactoryBean threw exception on  object: [" + beanName + "] creation", e);
        }
    }

}
