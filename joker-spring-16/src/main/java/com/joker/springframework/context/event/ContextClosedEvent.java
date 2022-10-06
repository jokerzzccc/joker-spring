package com.joker.springframework.context.event;

/**
 * <p>
 * Spring 自己的事件类：监听关闭动作
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/9/25
 */
public class ContextClosedEvent extends ApplicationContextEvent {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ContextClosedEvent(Object source) {
        super(source);
    }

}
