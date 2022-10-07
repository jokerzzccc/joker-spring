package com.joker.springframework.test;

import com.joker.springframework.context.support.ClassPathXmlApplicationContext;
import com.joker.springframework.core.convert.converter.Converter;
import com.joker.springframework.core.convert.support.StringToNumberConverterFactory;
import com.joker.springframework.test.bean.Husband;
import com.joker.springframework.test.converter.StringToIntegerConverter;
import org.junit.Test;

/**
 * @author jokerzzccc
 * @date 2022/9/18
 */
public class ApiTest {

    @Test
    public void test_convert() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        Husband husband = applicationContext.getBean("husband", Husband.class);
        System.out.println("convert 融入 Spring 的测试结果：" + husband);
    }

    @Test
    public void test_StringToIntegerConverter() {
        StringToIntegerConverter converter = new StringToIntegerConverter();
        Integer num = converter.convert("1234");
        System.out.println("StringToIntegerConverter 测试结果：" + num);
    }

    @Test
    public void test_StringToNumberConverterFactory() {
        StringToNumberConverterFactory converterFactory = new StringToNumberConverterFactory();

        Converter<String, Integer> stringToIntegerConverter = converterFactory.getConverter(Integer.class);
        System.out.println("stringToIntegerConverter 测试结果：" + stringToIntegerConverter.convert("1234"));

        Converter<String, Long> stringToLongConverter = converterFactory.getConverter(Long.class);
        System.out.println("stringToLongConverter 测试结果：" + stringToLongConverter.convert("1234"));
    }

}
