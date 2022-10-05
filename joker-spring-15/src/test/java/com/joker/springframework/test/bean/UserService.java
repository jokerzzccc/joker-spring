package com.joker.springframework.test.bean;

import com.joker.springframework.beans.factory.annotation.Autowired;
import com.joker.springframework.beans.factory.annotation.Value;
import com.joker.springframework.stereotype.Component;

import java.util.Random;

/**
 * <p>
 *
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/10/4
 */
public class UserService implements IUserService {

    private String token;

    @Override
    public String queryUserInfo() {
        try {
            Thread.sleep(new Random().nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "info: { jokerzzccc,0001, laughTale }" + ", token: " + token;
    }

    @Override
    public String register(String userName) {
        try {
            Thread.sleep(new Random().nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "register user: " + userName + " success";
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    @Override
    public String toString() {
        return "UserService{" +
                "token='" + token + '\'' +
                '}';
    }

}
