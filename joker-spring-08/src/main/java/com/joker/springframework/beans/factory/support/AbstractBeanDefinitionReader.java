package com.joker.springframework.beans.factory.support;

import com.joker.springframework.core.io.DefaultResourceReader;
import com.joker.springframework.core.io.ResourceLoader;

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

    private ResourceLoader resourceLoader;

    protected AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry, new DefaultResourceReader());
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
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
