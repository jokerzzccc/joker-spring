package com.joker.springframework.core.convert.converter;

/**
 * <p>
 * A converter converts a source object of type {@code S} to a target of type {@code T}.
 * </p>
 * 类型转换处理接口
 *
 * @param <S> the source type
 * @param <T> the target type
 * @author jokerzzccc
 * @date 2022/10/7
 */
public interface Converter<S, T> {

    /**
     * Convert the source object of type {@code S} to target type {@code T}.
     *
     * @param source the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return the converted object, which must be an instance of {@code T} (potentially {@code null})
     * @throws IllegalArgumentException if the source cannot be converted to the desired target type
     */
    T convert(S source);

}
