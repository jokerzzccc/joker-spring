package com.joker.springframework.context.support;

import com.joker.springframework.beans.BeansException;
import com.joker.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.joker.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * <p>
 * 获取 beanFactory 和 加载资源
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/9/21
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }

}
