package com.joker.springframework.beans.factory;

import com.joker.springframework.beans.BeansException;

/**
 * <p>
 * 初始化方法接口：做一些参数的初始化
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/9/23
 */
public interface InitializingBean {

    /**
     * Bean处理了属性填充后调用
     *
     * @throws BeansException
     */
    void afterPropertiesSet() throws Exception;

}
