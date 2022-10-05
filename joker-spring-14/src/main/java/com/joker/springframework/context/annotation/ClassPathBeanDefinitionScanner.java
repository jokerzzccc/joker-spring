package com.joker.springframework.context.annotation;

import cn.hutool.core.util.StrUtil;
import com.joker.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import com.joker.springframework.beans.factory.config.BeanDefinition;
import com.joker.springframework.beans.factory.support.BeanDefinitionRegistry;
import com.joker.springframework.stereotype.Component;

import java.util.Set;

/**
 * A bean definition scanner that detects bean candidates on the classpath,
 * registering corresponding bean definitions with a given registry ({@code BeanFactory}
 * or {@code ApplicationContext}).
 * <p>
 * 具体扫描包处理的类：
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/10/5
 */
public class ClassPathBeanDefinitionScanner extends ClassPathScanningCandidateComponentProvider {

    private BeanDefinitionRegistry registry;

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    /**
     * 除了获取到扫描到的类信息以后，还需要获取 Bean 的作用域和类名，如果不配置类名，基本都是首字母缩写。
     *
     * @param basePackages
     */
    public void doScan(String... basePackages) {
        for (String basePackage : basePackages) {
            Set<BeanDefinition> candidates = findCandidateComponent(basePackage);
            for (BeanDefinition beanDefinition : candidates) {
                // 解析 Bean 的作用域
                String beanScope = resolveBeanScope(beanDefinition);
                if (StrUtil.isNotEmpty(beanScope)) {
                    beanDefinition.setScope(beanScope);
                }
                registry.registerBeanDefinition(determineBeanName(beanDefinition), beanDefinition);
            }
        }

        // 注册处理注解的 BeanPostProcessor (@Autowired, @Value)
        registry.registerBeanDefinition("com.joker.springframework.context.annotation.internalAutowiredAnnotationProcessor"
                , new BeanDefinition(AutowiredAnnotationBeanPostProcessor.class));
    }

    /**
     * 解析 bean 的作用域
     *
     * @param beanDefinition
     * @return
     */
    private String resolveBeanScope(BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        Scope scope = beanClass.getAnnotation(Scope.class);
        if (null != scope) {
            return scope.value();
        }
        return StrUtil.EMPTY;
    }

    /**
     * 得到 Bean 名称
     *
     * @param beanDefinition
     * @return
     */
    private String determineBeanName(BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        Component component = beanClass.getAnnotation(Component.class);
        String value = component.value();
        if (StrUtil.isEmpty(value)) {
            value = StrUtil.lowerFirst(beanClass.getSimpleName());
        }
        return value;
    }

}
