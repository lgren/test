package com.lgren.jyyh_jsp.base;



import com.lgren.反射.LReflectionUtils;

import java.lang.reflect.Field;

/**
 * TODO
 * @create 2019-06-21 15:49
 * @since lgren
 */
public interface TagBeginEnd {
    // private String tagBegin;
    // private String tagEnd;

    default String getTagBegin() {
        Class<? extends TagBeginEnd> thisClass = this.getClass();
        Field[] fields = LReflectionUtils.findFields(thisClass);
        // LReflectionUtils.getMethod(thisClass, );
        return "";
    }
    // TagBeginEnd setTagBegin(String tagBegin);

    // String getTagEnd();

    // TagBeginEnd setTagEnd(String tagEnd);
}
