package com.joker.springframework.context.event;

import com.joker.springframework.beans.factory.BeanFactory;
import com.joker.springframework.context.ApplicationEvent;
import com.joker.springframework.context.ApplicationListener;

/**
 * <p>
 * Simple implementation of the {@link ApplicationEventMulticaster} interface.
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/9/25
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster{

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void multicastEvent(final ApplicationEvent event) {
        for (final ApplicationListener listener : getApplicationListeners(event)) {
            listener.onApplicationEvent(event);
        }
    }

}
