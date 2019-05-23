package com.lgren.反射;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * TODO
 * @author lgren
 * @create 2019-05-22 15:21
 **/
public class LReflectionUtils {
    public static Method getMethod(Class<?> clazz, String field) {
        Method rMethod = null;
        try {
            rMethod = clazz.getDeclaredMethod(field);
        } catch (NoSuchMethodException ignored) { }
        if (rMethod == null && clazz.getSuperclass() != null) {
            rMethod = getMethod(clazz.getSuperclass(), field);
        }
        return rMethod;
    }

    public static Field[] getFields(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        Class<?> tmp = clazz.getSuperclass();
        while (tmp != null) {
            Field[] superFields = tmp.getDeclaredFields();
            Field[] allFields = new Field[fields.length + superFields.length];
            System.arraycopy(fields, 0, allFields, 0, fields.length);
            System.arraycopy(superFields, 0, allFields, fields.length, superFields.length);
            fields = allFields;
            tmp = tmp.getSuperclass();
        }
        return fields;
    }

    public static void main(String[] args) {

        getMethod(TestClass.class, "getName1");
        System.out.println();
    }
}
