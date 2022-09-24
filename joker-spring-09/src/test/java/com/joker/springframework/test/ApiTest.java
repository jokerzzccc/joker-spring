package com.joker.springframework.test;

import com.joker.springframework.context.support.ClassPathXmlApplicationContext;
import com.joker.springframework.test.bean.UserService;
import org.junit.Test;

import java.io.InputStream;

/**
 * <p>
 * 核心实现类：接口定义了注册，抽象类定义了获取，都集中在
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/9/18
 */
public class ApiTest {
    /**
     * 测试：使用应用上下文
     *
     * @throws Exception
     */
    @Test
    public void test_xml() throws Exception {
        // 1. 初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();
        // 2. 获取 bean 对象调用方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
        System.out.println("ApplicationContextAware: " + userService.getApplicationContext());
        System.out.println("BeanFactoryAware: " + userService.getBeanFactory());
    }

}
