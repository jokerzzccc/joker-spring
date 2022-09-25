package com.joker.springframework.test.bean;

import com.joker.springframework.beans.BeansException;
import com.joker.springframework.beans.factory.*;
import com.joker.springframework.context.ApplicationContext;
import com.joker.springframework.context.ApplicationContextAware;

/**
 * <p>
 *
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/9/18
 */
public class UserService {

    private String uId;

    private String company;

    private String location;

    private IUserDao userDao;

    public String queryUserInfo() {
        return userDao.queryUserName(uId) + ", company: " + company + ", location: " + location;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public IUserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

}
