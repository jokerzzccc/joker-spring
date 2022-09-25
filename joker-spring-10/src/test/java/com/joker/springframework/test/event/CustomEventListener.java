package com.joker.springframework.test.event;

import com.joker.springframework.context.ApplicationListener;

import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/9/25
 */
public class CustomEventListener implements ApplicationListener<CustomEvent> {

    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println(LocalDateTime.now() + ": get message : " +
                "[ id:" + event.getId() + ", message:" + event.getMessage() + " ] " +
                "from event source:" + event.getSource());
    }

}
