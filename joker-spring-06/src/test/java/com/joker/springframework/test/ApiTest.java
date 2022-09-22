package com.joker.springframework.test;

import cn.hutool.core.io.IoUtil;
import com.joker.springframework.beans.PropertyValue;
import com.joker.springframework.beans.PropertyValues;
import com.joker.springframework.beans.factory.config.BeanDefinition;
import com.joker.springframework.beans.factory.config.BeanReference;
import com.joker.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.joker.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.joker.springframework.context.support.ClassPathXmlApplicationContext;
import com.joker.springframework.core.io.DefaultResourceReader;
import com.joker.springframework.core.io.Resource;
import com.joker.springframework.test.bean.UserDao;
import com.joker.springframework.test.bean.UserService;
import com.joker.springframework.test.common.MyBeanFactoryPostProcessor;
import com.joker.springframework.test.common.MyBeanPostProcessor;
import org.junit.Before;
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
     * 不用 应用上下文
     */
    @Test
    public void test_BeanFactoryPostProcessorAndBeanPostProcessor() {
        // 1. 初始化 BeanFactory
        final DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 读取配置文件 & 注册 bean
        final XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        // 3. BeanDefinition 加载完成 & bean 实例化之前， 修改 BeanDefinition 的属性值
        final MyBeanFactoryPostProcessor myBeanFactoryPostProcessor = new MyBeanFactoryPostProcessor();
        myBeanFactoryPostProcessor.postProcessBeanFactory(beanFactory);

        // 4. Bean 实例化之后，修改 Bean 属性信息
        final MyBeanPostProcessor myBeanPostProcessor = new MyBeanPostProcessor();
        beanFactory.addBeanPostProcessor(myBeanPostProcessor);

        // 5. 获取 Bean 对象调用方法
        final UserService userService = beanFactory.getBean("userService", UserService.class);
        final String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);

    }

    /**
     * 测试：使用应用上下文
     *
     * @throws Exception
     */
    @Test
    public void test_xml() throws Exception {
        // 1. 初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:springPostProcessor.xml");
        // 2. 获取 bean 对象调用方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
    }

}
