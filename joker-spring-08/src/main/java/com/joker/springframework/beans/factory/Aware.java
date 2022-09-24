package com.joker.springframework.beans.factory;

/**
 * <p>
 * Marker superinterface indicating that a bean is eligible to be
 * notified by the Spring container of a particular framework object
 * through a callback-style method.  Actual method signature is
 * determined by individual subinterfaces, but should typically
 * consist of just one void-returning method that accepts a single
 * argument.
 * </p>
 * 标记类接口，实现该接口，可以被 spring 容器所感知到
 *
 * @author jokerzzccc
 * @date 2022/9/24
 */
public interface Aware {

}
