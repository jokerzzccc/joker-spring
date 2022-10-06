package com.joker.springframework.aop.framework.autoproxy;

import com.joker.springframework.aop.AdvisedSupport;
import com.joker.springframework.aop.Advisor;
import com.joker.springframework.aop.ClassFilter;
import com.joker.springframework.aop.TargetSource;
import com.joker.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import com.joker.springframework.aop.framework.ProxyFactory;
import com.joker.springframework.beans.BeansException;
import com.joker.springframework.beans.PropertyValues;
import com.joker.springframework.beans.factory.BeanFactory;
import com.joker.springframework.beans.factory.BeanFactoryAware;
import com.joker.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.joker.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aspectj.weaver.patterns.Pointcut;

import java.util.Collection;

/**
 * {@code BeanPostProcessor} implementation that creates AOP proxies based on all
 * candidate {@code Advisor}s in the current {@code BeanFactory}. This class is
 * completely generic; it contains no special code to handle any particular aspects,
 * such as pooling aspects.
 * <p>
 * 融入 Bean 生命周期的自动代理创建者。
 * DefaultAdvisorAutoProxyCreator 类就是用于处理 AOP 融入到 Bean 的生命周期的核心类。
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/10/4
 */
public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private DefaultListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        return true;
    }

    /**
     * 判断是否是 AOP 相关的子类
     *
     * @param beanClass
     * @return
     */
    private boolean isInfrastructureClass(Class<?> beanClass) {
        return Advice.class.isAssignableFrom(beanClass)
                || Pointcut.class.isAssignableFrom(beanClass)
                || Advisor.class.isAssignableFrom(beanClass);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        if (isInfrastructureClass(beanClass)) {
            return null;
        }

        Collection<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeansOfType(AspectJExpressionPointcutAdvisor.class).values();
        for (AspectJExpressionPointcutAdvisor advisor : advisors) {
            ClassFilter classFilter = advisor.getPointcut().getClassFilter();
            // 过来匹配类
            if (!classFilter.matches(beanClass)) {
                continue;
            }

            AdvisedSupport advisedSupport = new AdvisedSupport();

            TargetSource targetSource = new TargetSource(bean);
            advisedSupport.setTargetSource(targetSource);
            advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            advisedSupport.setProxyTargetClass(false);

            //返回代理对象
            return new ProxyFactory(advisedSupport).getProxy();
        }
        return bean;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        return pvs;
    }

}
