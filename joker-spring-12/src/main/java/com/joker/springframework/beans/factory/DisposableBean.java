package com.joker.springframework.beans.factory;

/**
 * <p>
 * 销毁方法接口：
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/9/23
 */
public interface DisposableBean {

    void destroy() throws Exception;

}
