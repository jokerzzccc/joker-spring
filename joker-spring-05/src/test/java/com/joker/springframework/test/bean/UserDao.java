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

    static {
        map.put("0001", " jokerzzccc");
        map.put("0002", " jokerzzc");
        map.put("0003", " jokerzz");
    }

    public String  queryUserName(String uId) {
        return map.get(uId);
    }

}
