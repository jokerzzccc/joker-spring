package com.joker.springframework.test;

import cn.hutool.core.io.IoUtil;
import com.joker.springframework.beans.PropertyValue;
import com.joker.springframework.beans.PropertyValues;
import com.joker.springframework.beans.factory.config.BeanDefinition;
import com.joker.springframework.beans.factory.config.BeanReference;
import com.joker.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.joker.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.joker.springframework.core.io.DefaultResourceReader;
import com.joker.springframework.core.io.Resource;
import com.joker.springframework.test.bean.UserDao;
import com.joker.springframework.test.bean.UserService;
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

    private DefaultResourceReader resourceReader;

    @Before
    public void init() throws Exception {
        resourceReader = new DefaultResourceReader();
    }

    @Test
    public void testBeanFactory() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2.UserDao 注册
        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));

        // 3. UserService 设置属性
        final PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uId", "0001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));

        // 4. UserService 注入 bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 5. UserService 获取 bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }

    @Test
    public void test_classPath() throws Exception {
        Resource resource = resourceReader.getResource("classpath:important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void test_file() throws Exception {
        Resource resource = resourceReader.getResource("src/resources/important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void test_url() throws Exception {
        Resource resource = resourceReader.getResource("https://github.com/fuzhengwei/small-spring/important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    /**
     * 测试：配置文件注册 bean
     *
     * @throws Exception
     */
    @Test
    public void test_xml() throws Exception {
        // 1. 初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 2. 读取配置文件 & 注册 bean
        final XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        // 3. 读取 bean 对象调用方法
        UserService userService = beanFactory.getBean("userService", UserService.class);
        final String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
    }

}
