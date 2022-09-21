package com.joker.springframework.beans.factory.config;

/**
 * <p>
 * bean 的单例模式：获取单例对象的接口
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/9/18
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String name);

}
