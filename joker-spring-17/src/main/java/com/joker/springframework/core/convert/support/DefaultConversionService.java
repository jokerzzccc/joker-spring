package com.joker.springframework.core.convert.support;

import com.joker.springframework.core.convert.ConversionService;
import com.joker.springframework.core.convert.converter.ConverterRegistry;

/**
 * <p>
 * A specialization of {@link GenericConversionService} configured by default
 * with converters appropriate for most environments.
 * </p>
 * 默认实现类型转换服务
 *
 * @author jokerzzccc
 * @date 2022/10/7
 */
public class DefaultConversionService extends GenericConversionService {

    /**
     * Create a new {@code DefaultConversionService} with the set of
     * {@linkplain DefaultConversionService#addDefaultConverters(ConverterRegistry) default converters}.
     */
    public DefaultConversionService() {
        addDefaultConverters(this);
    }

    /**
     * Add converters appropriate for most environments.
     * @param converterRegistry the registry of converters to add to
     * (must also be castable to ConversionService, e.g. being a {@link ConfigurableConversionService})
     * @throws ClassCastException if the given ConverterRegistry could not be cast to a ConversionService
     */
    public static void addDefaultConverters(ConverterRegistry converterRegistry) {
        // 添加各类类型转换工厂
        converterRegistry.addConverterFactory(new StringToNumberConverterFactory());
    }

}
