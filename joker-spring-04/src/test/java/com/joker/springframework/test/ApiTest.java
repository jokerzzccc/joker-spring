package com.joker.springframework.test;

import com.joker.springframework.beans.factory.config.BeanDefinition;
import com.joker.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.joker.springframework.test.bean.UserService;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * <p>
 * 核心实现类：接口定义了注册，抽象类定义了获取，都集中在
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/9/18
 */
public class ApiTest {

    @Test
    public void testBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        UserService userService = (UserService) beanFactory.getBean("userService", "jokerzzccc");
        userService.queryUserInfo();
    }

    @Test
    public void testNewInstance() throws IllegalAccessException, InstantiationException {
        UserService userService = UserService.class.newInstance();
        System.out.println(userService);
    }

    @Test
    public void testConstructor() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<UserService> userServiceClass = UserService.class;
        Constructor<UserService> declaredConstructor = userServiceClass.getDeclaredConstructor(String.class);
        UserService userService = declaredConstructor.newInstance("jokerzzccc");
        System.out.println(userService);
    }

    @Test
    public void testParameters() throws Exception {
        Class<UserService> beanClass = UserService.class;
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        Constructor<?> constructor = declaredConstructors[0];
        Constructor<UserService> declaredConstructor = beanClass.getDeclaredConstructor(constructor.getParameterTypes());
        final UserService userService = declaredConstructor.newInstance("jokerzzccc");
        System.out.println(userService);
    }

    /**
     * 测试 Cglib 实例化
     *
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    @Test
    public void testCglib() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        final Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserService.class);
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        final Object obj = enhancer.create(new Class[]{String.class}, new Object[]{"jokerzzccc"});
        System.out.println(obj);

    }

}
