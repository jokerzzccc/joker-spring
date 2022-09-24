package com.joker.springframework.test.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/9/20
 */
public class UserDao {

    private static Map<String, String> map = new HashMap<>();


    public void initDataMethod() {
        System.out.println("执行：init-method");
        map.put("0001", " jokerzzccc");
        map.put("0002", " jokerzzc");
        map.put("0003", " jokerzz");
    }

    public void destroyDataMethod() {
        System.out.println("执行：destroy-method");
        map.clear();
    }
    public String  queryUserName(String uId) {
        return map.get(uId);
    }

}
