package com.joker.springframework.test.event;

import com.joker.springframework.context.ApplicationEvent;
import com.joker.springframework.context.event.ApplicationContextEvent;

/**
 * <p>
 * 创建一个自定义事件，在事件类的构造函数中可以添加自己想要的入参信息。这个事件类最终会被完整的拿到监听里，所以你添加的属性都会被获得
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/9/25
 */
public class CustomEvent extends ApplicationContextEvent {

    private Long id;

    private String message;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public CustomEvent(Object source, Long id, String message) {
        super(source);
        this.id = id;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
