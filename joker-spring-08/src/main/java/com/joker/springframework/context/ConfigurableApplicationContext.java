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
     * 刷新容器：refresh 方法就是整个 spring 容器的操作过程。
     *
     * @throws BeansException
     */
    void refresh() throws BeansException;

    /**
     * 注册虚拟机的钩子方法
     */
    void registerShutdownHook();

    /**
     * 手动执行关闭的方法
     */
    void close();

}
