package com.joker.springframework.context.event;

/**
 * <p>
 * Spring 自己的事件类：监听刷新动作
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/9/25
 */
public class ContextRefreshedEvent extends ApplicationContextEvent {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ContextRefreshedEvent(Object source) {
        super(source);
    }

}
