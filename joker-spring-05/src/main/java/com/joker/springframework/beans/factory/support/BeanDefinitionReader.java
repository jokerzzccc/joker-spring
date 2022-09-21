package com.joker.springframework.beans.factory.support;

import com.joker.springframework.beans.BeansException;
import com.joker.springframework.beans.factory.config.BeanDefinition;
import com.joker.springframework.core.io.Resource;
import com.joker.springframework.core.io.ResourceLoader;

/**
 * <p>
 * bean 定义读取接口（bean definition readers):
 * 注意：getRegistry()、getResourceLoader() 都是提供给后面三个加载 bean 定义的方法的工具，注册和加载，这两个方法的实现会包装
 * 到抽象类中，以免污染具体的实现接口。
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/9/20
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resources) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;

}
