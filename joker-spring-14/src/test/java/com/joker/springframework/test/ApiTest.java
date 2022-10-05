package com.joker.springframework.test;

import com.joker.springframework.beans.BeansException;
import com.joker.springframework.beans.factory.config.BeanPostProcessor;
import com.joker.springframework.context.support.ClassPathXmlApplicationContext;
import com.joker.springframework.test.bean.IUserService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jokerzzccc
 * @date 2022/9/18
 */
public class ApiTest {

    /**
     * 测试注解注入属性和对象
     */
    @Test
    public void test_scan() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println("注解注入属性和对象 测试结果：" + userService.queryUserInfo());
    }

}
