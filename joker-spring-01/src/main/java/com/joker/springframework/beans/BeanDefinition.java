package com.joker.springframework.beans;

/**
 * <p>
 *
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/9/17
 */
public class BeanDefinition {

	private Object bean;

	public BeanDefinition(Object bean) {
		this.bean = bean;
	}

	public Object getBean() {
		return bean;
	}

	public void setBean(Object bean) {
		this.bean = bean;
	}
}
