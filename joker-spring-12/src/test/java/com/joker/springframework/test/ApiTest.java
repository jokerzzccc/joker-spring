package com.joker.springframework.test;

import com.joker.springframework.aop.AdvisedSupport;
import com.joker.springframework.aop.ClassFilter;
import com.joker.springframework.aop.MethodMatcher;
import com.joker.springframework.aop.TargetSource;
import com.joker.springframework.aop.aspectj.AspectJExpressionPointcut;
import com.joker.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import com.joker.springframework.aop.framework.Cglib2AopProxy;
import com.joker.springframework.aop.framework.JdkDynamicAopProxy;
import com.joker.springframework.aop.framework.ProxyFactory;
import com.joker.springframework.aop.framework.ReflectiveMethodInvocation;
import com.joker.springframework.aop.framework.adapter.MethodBeforeAdviceInterceptor;
import com.joker.springframework.context.support.ClassPathXmlApplicationContext;
import com.joker.springframework.test.bean.IUserService;
import com.joker.springframework.test.bean.UserService;
import com.joker.springframework.test.bean.UserServiceBeforeAdvice;
import com.joker.springframework.test.bean.UserServiceInterceptor;
import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author jokerzzccc
 * @date 2022/9/18
 */
public class ApiTest {

    private AdvisedSupport advisedSupport;

    @Before
    public void init() {
        // 目标对象
        UserService userService = new UserService();
        // 组装代理信息
        advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* com.joker.springframework.test.bean.IUserService" +
                ".*(..))"));
    }

    @Test
    public void test_proxyFactory() {
        advisedSupport.setProxyTargetClass(false);
        final IUserService proxy = (IUserService) new ProxyFactory(advisedSupport).getProxy();
        System.out.println("代理工厂测试结果：" + proxy.queryUserInfo());
    }

    @Test
    public void test_beforeAdvice() {
        final UserServiceBeforeAdvice beforeAdvice = new UserServiceBeforeAdvice();
        final MethodBeforeAdviceInterceptor interceptor = new MethodBeforeAdviceInterceptor(beforeAdvice);
        advisedSupport.setMethodInterceptor(interceptor);

        IUserService proxy = (IUserService) new ProxyFactory(advisedSupport).getProxy();
        System.out.println("beforeAdvice 测试结果：" + proxy.queryUserInfo());
    }

    @Test
    public void test_advisor() {
        // 目标对象
        final UserService userService = new UserService();

        final AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        advisor.setExpression("execution(* com.joker.springframework.test.bean.IUserService.*(..))");
        advisor.setAdvice(new MethodBeforeAdviceInterceptor(new UserServiceBeforeAdvice()));

        final ClassFilter classFilter = advisor.getPointcut().getClassFilter();
        if (classFilter.matches(userService.getClass())) {
            final AdvisedSupport advisedSupport = new AdvisedSupport();
            final TargetSource targetSource = new TargetSource(userService);

            advisedSupport.setTargetSource(targetSource);
            advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            // false/true，JDK动态代理、CGlib动态代理
            advisedSupport.setProxyTargetClass(true);

            IUserService proxy = (IUserService) new ProxyFactory(advisedSupport).getProxy();
            System.out.println("advisor 测试结果：" + proxy.queryUserInfo());
        }

    }

    @Test
    public void test_aop() throws NoSuchMethodException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");

        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println("AOP 融进 Spring 动态代理测试结果：" + userService.queryUserInfo());
    }

    /**
     * 一个代理方法的案例：先了解下未解耦之前代理方法的核心全貌
     */
    @Test
    public void test_proxy_method() {
        // 目标对象
        final UserService targetObj = new UserService();
        // AOP 代理对象
        final IUserService proxyObj = (IUserService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader()
                , targetObj.getClass().getInterfaces()
                , new InvocationHandler() {
                    // 方法匹配器
                    final MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* com.joker.springframework.test.bean.IUserService" +
                            ".*(..))");

                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        // 方法拦截器
                        if (methodMatcher.matches(method, targetObj.getClass())) {
                            MethodInterceptor methodInterceptor = invocation -> {
                                final long start = System.currentTimeMillis();
                                try {
                                    return invocation.proceed();
                                } finally {
                                    System.out.println("监控 - Begin By AOP");
                                    System.out.println("方法名称：" + invocation.getMethod());
                                    System.out.println("方法耗时：" + (System.currentTimeMillis() - start) + "ms");
                                    System.out.println("监控 - End\r\n");
                                }
                            };
                            // 反射调用
                            return methodInterceptor.invoke(new ReflectiveMethodInvocation(targetObj, method, args));
                        }
                        return method.invoke(targetObj, args);
                    }
                });

        System.out.println("代理方法测试结果：" + proxyObj.queryUserInfo());

    }

}
