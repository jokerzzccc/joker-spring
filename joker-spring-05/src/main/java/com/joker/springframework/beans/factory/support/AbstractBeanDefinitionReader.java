package com.joker.springframework.beans.factory.support;

import com.joker.springframework.core.io.DefaultResourceLoader;
import com.joker.springframework.core.io.ResourceLoader;
import com.sun.istack.internal.Nullable;

/**
 * <p>
 * beanDefinitonReader 的抽象类实现
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/9/20
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    private final BeanDefinitionRegistry registry;

    @Nullable
    private ResourceLoader resourceLoader;

    protected AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry, new DefaultResourceLoader());
    }

    protected AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }

}
