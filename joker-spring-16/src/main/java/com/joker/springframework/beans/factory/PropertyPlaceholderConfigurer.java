package com.joker.springframework.beans.factory;

import cn.hutool.core.bean.BeanException;
import com.joker.springframework.beans.PropertyValue;
import com.joker.springframework.beans.PropertyValues;
import com.joker.springframework.beans.factory.config.BeanDefinition;
import com.joker.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.joker.springframework.core.io.DefaultResourceReader;
import com.joker.springframework.core.io.Resource;
import com.joker.springframework.util.StringValueResolver;

import java.io.IOException;
import java.util.Properties;

/**
 * <p>
 * 处理占位符配置:
 * 在 Bean 对象实例化之前，改变属性信息。
 * 这里就是完成对配置文件的加载，以及摘取占位符中的在属性文件里的配置。
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/10/5
 */
public class PropertyPlaceholderConfigurer implements BeanFactoryPostProcessor {

    /**
     * Default placeholder prefix: {@value}.
     */
    public static final String DEFAULT_PLACEHOLDER_PREFIX = "${";

    /**
     * Default placeholder suffix: {@value}.
     */
    public static final String DEFAULT_PLACEHOLDER_SUFFIX = "}";

    private String location;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeanException {
        try {
            // 加载属性文件
            DefaultResourceReader resourceLoader = new DefaultResourceReader();
            Resource resource = resourceLoader.getResource(location);
            Properties properties = new Properties();
            properties.load(resource.getInputStream());

            // 占位符替换属性值、设置属性值
            String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
            for (String beanName : beanDefinitionNames) {
                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
                PropertyValues propertyValues = beanDefinition.getPropertyValues();
                for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                    Object value = propertyValue.getValue();
                    if (!(value instanceof String)) {
                        continue;
                    }
                    String strVal = (String) value;
                    StringBuilder buffer = new StringBuilder(strVal);
                    int startIdx = strVal.indexOf(DEFAULT_PLACEHOLDER_PREFIX);
                    int stopIdx = strVal.indexOf(DEFAULT_PLACEHOLDER_SUFFIX);
                    if (startIdx != -1 && stopIdx != -1 && startIdx < stopIdx) {
                        String propKey = strVal.substring(startIdx + 2, stopIdx);
                        String propVal = properties.getProperty(propKey);
                        buffer.replace(startIdx, stopIdx + 1, propVal);
                        propertyValues.addPropertyValue(new PropertyValue(propertyValue.getName(), buffer.toString()));
                    }
                }
            }

            // 向容器中添加字符串解析器，供解析 @Value 使用
            PlaceholderResolvingStringValueResolver valueResolver = new PlaceholderResolvingStringValueResolver(properties);
            beanFactory.addEmbeddedValueResolver(valueResolver);

        } catch (IOException e) {
            throw new BeanException("Could not load properties from location: " + location, e);
        }
    }

    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Resolve the given placeholder using the given properties,
     *
     * @param value
     * @param properties
     * @return
     */
    private String resolvePlaceholder(String value, Properties properties) {
        String strVal = value;
        StringBuilder buffer = new StringBuilder(strVal);
        int startIdx = strVal.indexOf(DEFAULT_PLACEHOLDER_PREFIX);
        int stopIdx = strVal.indexOf(DEFAULT_PLACEHOLDER_SUFFIX);
        if (startIdx != -1 && stopIdx != -1 && startIdx < stopIdx) {
            final String propKey = strVal.substring(startIdx + 2, stopIdx);
            final String propVal = properties.getProperty(propKey);
            buffer.replace(startIdx, stopIdx + 1, propVal);
        }
        return buffer.toString();

    }

    private class PlaceholderResolvingStringValueResolver implements StringValueResolver {

        private final Properties properties;

        public PlaceholderResolvingStringValueResolver(Properties properties) {
            this.properties = properties;
        }

        @Override
        public String resolveStringValue(String strVal) {
            return PropertyPlaceholderConfigurer.this.resolvePlaceholder(strVal,
                    properties);
        }

    }

}
