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
@Component("userService")
public class UserService implements IUserService {

    @Value("${token}")
    private String token;

    @Autowired
    private UserDao userDao;

    @Override
    public String queryUserInfo() {
        try {
            Thread.sleep(new Random().nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return userDao.queryUserName("0001") + "," + token;
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

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public String toString() {
        return "UserService{" +
                "token='" + token + '\'' +
                '}';
    }

}
