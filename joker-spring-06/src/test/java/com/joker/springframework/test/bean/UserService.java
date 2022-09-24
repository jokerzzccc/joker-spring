package com.joker.springframework.test.bean;

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

    private UserDao userDao;

    public String queryUserInfo() {
        return userDao.queryUserName(uId) + " 号用户信息：company: " + company + ",location: " + location;
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

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

}
