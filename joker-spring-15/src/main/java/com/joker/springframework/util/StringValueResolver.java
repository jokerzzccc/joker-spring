package com.joker.springframework.util;

/**
 * Simple strategy interface for resolving a String value.
 * Used by {@link com.joker.springframework.beans.factory.config.ConfigurableBeanFactory}.
 * <p>
 * 解析字符串操作的接口
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/10/5
 */
public interface StringValueResolver {

    /**
     * Resolve the given String value, for example parsing placeholders.
     * 解析字符串：比如解析占位符
     *
     * @param strVal the original String value (never {@code null})
     * @return the resolved String value (may be {@code null} when resolved to a null
     * value), possibly the original String value itself (in case of no placeholders
     * to resolve or when ignoring unresolvable placeholders)
     * @throws IllegalArgumentException in case of an unresolvable String value
     */
    String resolveStringValue(String strVal);

}
