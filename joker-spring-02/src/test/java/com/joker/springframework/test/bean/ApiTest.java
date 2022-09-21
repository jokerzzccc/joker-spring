package com.joker.springframework.test.bean;

import com.joker.springframework.beans.factory.config.BeanDefinition;
import com.joker.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.joker.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.junit.Test;

/**
 * <p>
 *
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/9/18
 */
public class ApiTest {

    @Test
    public void testBeanFactory() {
        final DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        final BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);
        final UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
        final UserService userServiceSingleton = (UserService) beanFactory.getBean("userService");
        userServiceSingleton.queryUserInfo();
    }

}
