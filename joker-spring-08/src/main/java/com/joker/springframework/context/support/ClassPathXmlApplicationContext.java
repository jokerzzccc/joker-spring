package com.joker.springframework.context.support;

import com.joker.springframework.beans.BeansException;

/**
 * <p>
 * XML 文件应用上下文实现类:
 * 本类，是具体对外用户提供的应用上下文方法。
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/9/21
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {

    private String[] configLocations;

    /**
     * 从 XML 中加载 BeanDefinition, 并刷新上下文
     *
     * @param configLocation
     */
    public ClassPathXmlApplicationContext(String configLocation) {
        this(new String[]{configLocation});
    }

    /**
     * 从 XML 中加载 BeanDefinition, 并刷新上下文
     *
     * @param configLocations
     * @throws BeansException
     */
    public ClassPathXmlApplicationContext(String[] configLocations) throws BeansException {
        this.configLocations = configLocations;
        refresh();
    }

    @Override
    protected String[] getConfigurations() {
        return configLocations;
    }

}
