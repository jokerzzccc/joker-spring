package com.joker.springframework.core.io;

/**
 * <p>
 * 定义资源获取接口，里面传递 location 地址即可
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/9/20
 */
public interface ResourceLoader {

    /**
     * Pseudo URL prefix for loading from the class path: "classpath:"
     */
    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);

}
