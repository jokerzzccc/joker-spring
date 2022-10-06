package com.joker.springframework.test.bean;

/**
 * <p>
 *
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/10/6
 */
public class Husband {

    private Wife wife;

    public String queryWife() {
        return "Husband.wife";
    }

    public Wife getWife() {
        return wife;
    }

    public void setWife(Wife wife) {
        this.wife = wife;
    }

}
