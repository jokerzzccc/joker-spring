package com.joker.springframework.aop.framework.autoproxy;

import com.joker.springframework.aop.AdvisedSupport;
import com.joker.springframework.aop.Advisor;
import com.joker.springframework.aop.ClassFilter;
import com.joker.springframework.aop.TargetSource;
import com.joker.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import com.joker.springframework.aop.framework.ProxyFactory;
import com.joker.springframework.beans.BeansException;
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
        if (isInfrastructureClass(beanClass)) {
            return null;
        }

        Collection<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeansOfType(AspectJExpressionPointcutAdvisor.class).values();
        for (AspectJExpressionPointcutAdvisor advisor : advisors) {
            ClassFilter classFilter = advisor.getPointcut().getClassFilter();
            if (!classFilter.matches(beanClass)) {
                continue;
            }

            AdvisedSupport advisedSupport = new AdvisedSupport();

            TargetSource targetSource = null;
            try {
                targetSource = new TargetSource(beanClass.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
            advisedSupport.setTargetSource(targetSource);
            advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            advisedSupport.setProxyTargetClass(false);

            return new ProxyFactory(advisedSupport).getProxy();
        }
        return null;
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
        return bean;
    }

}
