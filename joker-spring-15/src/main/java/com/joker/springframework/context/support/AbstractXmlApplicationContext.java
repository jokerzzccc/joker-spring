package com.joker.springframework.context.support;

import com.joker.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.joker.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * <p>
 * 上下文中对配置信息的加载
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/9/21
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {

    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configurations = getConfigurations();
        if (null != configurations) {
            beanDefinitionReader.loadBeanDefinitions(configurations);
        }
    }

    protected abstract String[] getConfigurations();

}
