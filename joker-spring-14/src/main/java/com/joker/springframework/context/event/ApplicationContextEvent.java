package com.joker.springframework.context.event;

import com.joker.springframework.context.ApplicationContext;
import com.joker.springframework.context.ApplicationEvent;

/**
 * <p>
 * 定义事件的抽象类，所有的事件包括关闭、刷新、以及用户自己实现的事件
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/9/25
 */
public class ApplicationContextEvent extends ApplicationEvent {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    /**
     * Get the <code>ApplicationContext</code> that the event was raised for.
     */
    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }

}
