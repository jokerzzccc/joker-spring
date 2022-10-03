package com.joker.springframework.beans;

/**
 * <p>
 * 类的属性对象
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/9/20
 */
public class PropertyValue {

    private String name;
    private Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

}
