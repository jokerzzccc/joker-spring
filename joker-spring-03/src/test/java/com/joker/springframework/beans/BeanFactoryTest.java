package com.joker.springframework.beans;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * <p>
 *
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/9/17
 */
public class BeanFactoryTest extends TestCase {

    @Test
    public void testBeanFactory() {
        BeanFactory beanFactory = new BeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(new UserService());
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        final UserService userService = (UserService)beanFactory.getBean("userService");
        userService.queryUserInfo();
    }


}