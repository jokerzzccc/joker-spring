package com.joker.springframework.beans.factory;

import com.joker.springframework.beans.BeansException;

/**
 * <p>
 * 构造 bean 的工厂
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/9/18
 */
public interface BeanFactory {

    Object getBean(String name) throws BeansException;

}
