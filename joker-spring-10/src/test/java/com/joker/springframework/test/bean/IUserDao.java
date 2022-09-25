package com.joker.springframework.test.bean;

/**
 * <p>
 * 删掉 UserDao，定义 IUserDao 接口，为了通过 FactoryBean 做一个自定义对象的代理操作。
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/9/24
 */
public interface IUserDao {

    String queryUserName(String uId);

}
