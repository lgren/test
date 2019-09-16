package com.lgren.jyyh_jsp.tag;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IsNotAttr {
    boolean value() default true;
}
