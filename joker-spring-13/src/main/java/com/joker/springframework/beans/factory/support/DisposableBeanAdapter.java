package com.joker.springframework.beans.factory.support;

import cn.hutool.core.util.StrUtil;
import com.joker.springframework.beans.BeansException;
import com.joker.springframework.beans.factory.DisposableBean;
import com.joker.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * <p>
 * 定义销毁方法适配器（接口和配置）
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/9/23
 */
public class DisposableBeanAdapter implements DisposableBean {

    private final Object bean;

    private final String beanName;

    private String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws Exception {
        // 1. 实现接口 DisposableBean
        if (bean instanceof DisposableBean) {
            ((DisposableBean) bean).destroy();
        }
        // 2. 注解配置 destroy-method (判断是为了二次执行销毁）
        if (StrUtil.isNotEmpty(destroyMethodName) && !(bean instanceof DisposableBean && "destroy".equals(this.destroyMethodName))) {
            Method destroyMethod = bean.getClass().getMethod(destroyMethodName);
            if (null == destroyMethod) {
                throw new BeansException("Couldn't find a destroy method named " + destroyMethodName + " in bean with name '" + beanName + "'");
            }
            destroyMethod.invoke(bean);
        }

    }

}
