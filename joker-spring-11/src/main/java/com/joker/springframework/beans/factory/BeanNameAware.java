package com.joker.springframework.beans.factory;

/**
 * <p>
 * 实现此接口，即能感知到所属的 BeanName
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/9/24
 */
public interface BeanNameAware extends Aware{

    void setBeanName(String beanName);
}
