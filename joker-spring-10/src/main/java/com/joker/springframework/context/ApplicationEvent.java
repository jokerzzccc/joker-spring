package com.joker.springframework.context;

import java.util.EventObject;

/**
 * <p>
 * 定义出具备事件功能的抽象类，后续所以有事件的类都需要继承这个类
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/9/25
 */
public abstract class ApplicationEvent extends EventObject {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationEvent(Object source) {
        super(source);
    }

}
