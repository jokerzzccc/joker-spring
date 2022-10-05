package com.joker.springframework.test.common;

import com.joker.springframework.beans.BeansException;
import com.joker.springframework.beans.factory.config.BeanPostProcessor;
import com.joker.springframework.test.bean.UserService;


/**
 * <p>
 *
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/9/22
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("userService".equals(beanName)) {
            UserService userService = (UserService) bean;
            userService.setLocation("改为：重庆");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

}
