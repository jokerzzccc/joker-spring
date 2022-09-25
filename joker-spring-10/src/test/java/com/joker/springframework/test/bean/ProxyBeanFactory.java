package com.joker.springframework.test.bean;

import cn.hutool.core.bean.BeanException;
import com.joker.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 模拟了 UserDao 的原有功能，类似 MyBatis 框架中的代理操作
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/9/24
 */
public class ProxyBeanFactory implements FactoryBean<IUserDao> {

    @Override
    public IUserDao getObject() throws BeanException {
        InvocationHandler handler = (proxy, method, args) -> {
            Map<String, String> map = new HashMap<>();
            map.put("0001", " jokerzzccc");
            map.put("0002", " jokerzz");
            map.put("0003", " joker");
            return "你被代理了：" + method.getName() + ": " + map.get(args[0].toString());
        };
        return (IUserDao) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{IUserDao.class}, handler);

    }

    @Override
    public Class<?> getObjectType() {
        return IUserDao.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
