package com.joker.springframework.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * <p>
 * This annotation may be used on a field or parameter as a qualifier for
 * candidate beans when autowiring. It may also be used to annotate other
 * custom annotations that can then in turn be used as qualifiers.
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/10/5
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Inherited
@Documented
public @interface Qualifier {

    String value() default "";

}
