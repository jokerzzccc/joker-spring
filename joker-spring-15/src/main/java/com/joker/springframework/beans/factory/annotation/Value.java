package com.joker.springframework.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * <p>
 * Annotation used at the field or method/constructor parameter level
 * that indicates a default value expression for the annotated element.
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/10/5
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD})
@Documented
public @interface Value {

    /**
     * The actual value expression such as <code>#{systemProperties.myProp}</code>
     * or property placeholder such as <code>${my.app.myProp}</code>.
     */
    String value();

}
