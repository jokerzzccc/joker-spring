package com.joker.springframework.test.bean;

import com.joker.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/10/5
 */
@Component
public class UserDao {

    private static Map<String, String> map = new HashMap<>();

    static {
        map.put("0001", "joker, location1, location11");
        map.put("0002", "zoro, location2, location22");
        map.put("0003", "luffy, location3, location33");
    }

    public String queryUserName(String id) {
        return map.get(String.valueOf(id));
    }

}
