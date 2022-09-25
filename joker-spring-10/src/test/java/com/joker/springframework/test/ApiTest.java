package com.joker.springframework.test;

import com.joker.springframework.context.support.ClassPathXmlApplicationContext;
import com.joker.springframework.test.event.CustomEvent;
import org.junit.Test;

/**

 *
 * @author jokerzzccc
 * @date 2022/9/18
 */
public class ApiTest {


    @Test
    public void test_event() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext, 1238129814L, "success"));
        applicationContext.registerShutdownHook();
    }

}
