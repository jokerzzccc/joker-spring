package com.joker.springframework.context;

import com.joker.springframework.beans.BeansException;
import com.joker.springframework.beans.factory.Aware;

/**
 * <p>
 * 实现此接口，即能感知到所属的 ApplicationContext
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/9/24
 */
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
