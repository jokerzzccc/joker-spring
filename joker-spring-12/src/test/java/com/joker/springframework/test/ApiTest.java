package com.joker.springframework.test;

import com.joker.springframework.aop.AdvisedSupport;
import com.joker.springframework.aop.MethodMatcher;
import com.joker.springframework.aop.TargetSource;
import com.joker.springframework.aop.aspectj.AspectJExpressionPointcut;
import com.joker.springframework.aop.framework.Cglib2AopProxy;
import com.joker.springframework.aop.framework.JdkDynamicAopProxy;
import com.joker.springframework.aop.framework.ReflectiveMethodInvocation;
import com.joker.springframework.test.bean.IUserService;
import com.joker.springframework.test.bean.UserService;
import com.joker.springframework.test.bean.UserServiceInterceptor;
import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author jokerzzccc
 * @date 2022/9/18
 */
public class ApiTest {

    /**
     * 解耦之后，动态代理方法的实现
     */
    @Test
    public void test_dynamic() {
        // 目标对象
        final UserService userService = new UserService();

        // 组装代理信息
        final AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* com.joker.springframework.test.bean.IUserService" +
                ".*(..))"));

        // 代理方式一： 代理对象（JDK 代理)
        final IUserService proxy_jdk = (IUserService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        // 测试调用
        System.out.println("测试结果：" + proxy_jdk.queryUserInfo());

        System.out.println("================================================================");

        // 代理方式二： 代理对象（Cglib 代理)
        final IUserService proxy_cglib = (IUserService) new Cglib2AopProxy(advisedSupport).getProxy();
        // 测试调用
        System.out.println("测试结果：" + proxy_cglib.register("jokerzzccc"));

    }

    /**
     * 测试使用 Proxy.newProxyInstance() 代理方法是否成功
     */
    @Test
    public void test_proxy_class() {
        IUserService userService = (IUserService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{IUserService.class}, (proxy, method, args) -> "你被代理了！");
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
    }

    /**
     * 匹配方法的验证测试：查看拦截的方法与对应的对象是否匹配
     *
     * @throws NoSuchMethodException
     */
    @Test
    public void test_aop() throws NoSuchMethodException {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* com.joker.springframework.test.bean.UserService.*(..))");

        Class<UserService> clazz = UserService.class;
        Method method = clazz.getDeclaredMethod("queryUserInfo");

        System.out.println(pointcut.matches(clazz));
        System.out.println(pointcut.matches(method, clazz));
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
