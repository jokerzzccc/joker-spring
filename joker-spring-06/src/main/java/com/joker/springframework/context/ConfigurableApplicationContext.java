package com.joker.springframework.context;

import com.joker.springframework.beans.BeansException;

/**
 * <p>
 * 提供实现刷新容器的操作
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/9/21
 */
public interface ConfigurableApplicationContext extends ApplicationContext {

    /**
     * 刷新容器
     *
     * @throws BeansException
     */
    void refresh() throws BeansException;

}
