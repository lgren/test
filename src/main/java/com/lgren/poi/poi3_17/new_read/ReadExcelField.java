package com.lgren.poi.poi3_17.new_read;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ReadExcelField {
    String value() default "";
    int[] substring1() default {};

    String split2() default "";

    String strFormat3() default "";

    /**
     * 规则是 例如 "01-学生,02-教师" 左侧是映射值, 右侧是现在值
     */
    String mapping4() default "";

    String dateFormat99() default "yyyy-MM-dd HH:mm:ss";
}
