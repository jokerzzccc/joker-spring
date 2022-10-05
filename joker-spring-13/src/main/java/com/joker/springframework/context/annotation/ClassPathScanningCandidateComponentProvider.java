package com.joker.springframework.context.annotation;

import cn.hutool.core.util.ClassUtil;
import com.joker.springframework.beans.factory.config.BeanDefinition;
import com.joker.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * <p>
 * A component provider that provides candidate components from a base package.
 * </p>
 * 扫描出由 {@link Component} 注解的 Bean 对象
 * @author jokerzzccc
 * @date 2022/10/5
 */
public class ClassPathScanningCandidateComponentProvider {

    public Set<BeanDefinition> findCandidateComponent(String basePackage) {
        Set<BeanDefinition> candidates = new LinkedHashSet<>();
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);
        for (Class<?> clazz : classes) {
            candidates.add(new BeanDefinition(clazz));
        }
        return candidates;
    }

}
