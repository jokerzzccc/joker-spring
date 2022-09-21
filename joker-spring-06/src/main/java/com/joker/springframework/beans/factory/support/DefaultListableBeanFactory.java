package com.joker.springframework.beans.factory.support;

import com.joker.springframework.beans.BeansException;
import com.joker.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.joker.springframework.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 核心实现类：接口定义了注册，抽象类定义了获取，都集中在 DefaultListableBeanFactory 的 beanDefinitionMap 里
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/9/18
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    public BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) {
            throw new BeansException("Bean " + beanName + " undefined");
        }
        return beanDefinition;
    }

    @Override
    public boolean containsBeanDefinition(String beanName) throws BeansException {
        return beanDefinitionMap.containsKey(beanName);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> beanType) throws BeansException {
        Map<String, T> result = new HashMap<>();
        beanDefinitionMap.forEach((beanName, beanDefinition) -> {
            Class beanClass = beanDefinition.getBeanClass();
            if (beanType.isAssignableFrom(beanClass)) {
                result.put(beanName, (T) getBean(beanName));
            }
        });
        return result;
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return beanDefinitionMap.keySet().toArray(new String[0]);
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

}
