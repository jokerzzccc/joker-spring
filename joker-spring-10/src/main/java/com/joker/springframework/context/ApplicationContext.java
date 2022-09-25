package com.joker.springframework.context;

import com.joker.springframework.beans.factory.HierarchicalBeanFactory;
import com.joker.springframework.beans.factory.ListableBeanFactory;
import com.joker.springframework.core.io.ResourceLoader;

/**
 * <p>
 * 应用上下文
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/9/21
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher {

}
