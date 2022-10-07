package com.joker.springframework.core.convert;

import com.sun.istack.internal.Nullable;

/**
 * <p>
 * A service interface for type conversion. This is the entry point into the convert system.
 * Call {@link #convert(Object, Class)} to perform a thread-safe type conversion using this system.
 * </p>
 * 类型转换 抽象接口
 *
 * @author jokerzzccc
 * @date 2022/10/7
 */
public interface ConversionService {

    /**
     * Return {@code true} if objects of {@code sourceType} can be converted to the {@code targetType}.
     * <p>If this method returns {@code true}, it means {@link #convert(Object, Class)} is capable
     * of converting an instance of {@code sourceType} to {@code targetType}.
     *
     * @param sourceType the source type to convert from (may be {@code null} if source is {@code null})
     * @param targetType the target type to convert to (required)
     * @return {@code true} if a conversion can be performed, {@code false} if not
     * @throws IllegalArgumentException if {@code targetType} is {@code null}
     */
    boolean canConvert(@Nullable Class<?> sourceType, Class<?> targetType);

    /**
     * Convert the given {@code source} to the specified {@code targetType}.
     *
     * @param source the source object to convert (may be {@code null})
     * @param targetType the target type to convert to (required)
     * @return the converted object, an instance of targetType
     * @throws IllegalArgumentException if targetType is {@code null}
     */
    <T> T convert(Object source, Class<T> targetType);

}
