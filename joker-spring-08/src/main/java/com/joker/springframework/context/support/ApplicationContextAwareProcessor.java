package com.joker.springframework.context.support;

import com.joker.springframework.beans.BeansException;
import com.joker.springframework.beans.factory.config.BeanPostProcessor;
import com.joker.springframework.context.ApplicationContext;
import com.joker.springframework.context.ApplicationContextAware;

/**
 * <p>
 * 包装处理器
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/9/24
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {
    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
       if (bean instanceof ApplicationContextAware){
           ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
       }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

}
