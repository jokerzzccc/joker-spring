package com.joker.springframework.context.support;

import com.joker.springframework.beans.factory.FactoryBean;
import com.joker.springframework.beans.factory.InitializingBean;
import com.joker.springframework.core.convert.ConversionService;
import com.joker.springframework.core.convert.converter.Converter;
import com.joker.springframework.core.convert.converter.ConverterFactory;
import com.joker.springframework.core.convert.converter.ConverterRegistry;
import com.joker.springframework.core.convert.converter.GenericConverter;
import com.joker.springframework.core.convert.support.DefaultConversionService;
import com.joker.springframework.core.convert.support.GenericConversionService;
import com.sun.istack.internal.Nullable;

import java.util.Set;

/**
 * <p>
 * A factory providing convenient access to a ConversionService configured with
 * converters appropriate for most environments. Set the
 * {@link #setConverters "converters"} property to supplement the default converters.
 * </p>
 * 提供创建 ConversionServiceFactory
 *
 * @author jokerzzccc
 * @date 2022/10/7
 */
public class ConversionServiceFactoryBean implements FactoryBean<ConversionService>, InitializingBean {

    @Nullable
    private Set<?> converters;

    @Nullable
    private GenericConversionService conversionService;

    @Override
    public ConversionService getObject() throws Exception {
        return this.conversionService;
    }

    @Override
    public Class<?> getObjectType() {
        return conversionService.getClass();
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.conversionService = new DefaultConversionService();
        registerConverters(converters, conversionService);
    }

    /**
     * Register the given Converter objects with the given target ConverterRegistry.
     *
     * @param converters the converter objects: implementing {@link Converter},
     * {@link ConverterFactory}, or {@link GenericConverter}
     * @param registry the target registry
     */
    private void registerConverters(Set<?> converters, ConverterRegistry registry) {
        if (converters == null) {
            for (Object converter : converters) {
                if (converters instanceof GenericConverter) {
                    registry.addConverter((GenericConverter) converter);
                } else if (converters instanceof Converter<?, ?>) {
                    registry.addConverter(((Converter<?, ?>) converter));
                } else if (converters instanceof ConverterFactory<?, ?>) {
                    registry.addConverterFactory((ConverterFactory<?, ?>) converter);
                } else {
                    throw new IllegalStateException("Each converter object must implement one of the " +
                            "Converter, ConverterFactory, or GenericConverter interfaces");
                }
            }
        }
    }

    /**
     * Configure the set of custom converter objects that should be added:
     * implementing {@link com.joker.springframework.core.convert.converter.Converter},
     * {@link com.joker.springframework.core.convert.converter.ConverterFactory},
     * or {@link com.joker.springframework.core.convert.converter.GenericConverter}.
     */
    public void setConverters(Set<?> converters) {
        this.converters = converters;
    }

}
