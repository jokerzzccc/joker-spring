package com.joker.springframework.test;

import com.joker.springframework.context.support.ClassPathXmlApplicationContext;
import com.joker.springframework.test.bean.Husband;
import com.joker.springframework.test.bean.Wife;
import org.junit.Test;

/**
 * @author jokerzzccc
 * @date 2022/9/18
 */
public class ApiTest {

    /**
     * 测试循环依赖:
     * husband 依赖 wife ，wife 依赖 husband 和 mother，最后是关于 AOP 切面的依赖使用
     */
    @Test
    public void test_circular() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        Husband husband = applicationContext.getBean("husband", Husband.class);
        Wife wife = applicationContext.getBean("wife", Wife.class);
        System.out.println("老公的媳妇：" + husband.queryWife());
        System.out.println("媳妇的老公：" + wife.queryHusband());
    }

}
