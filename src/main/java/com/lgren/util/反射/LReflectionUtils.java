package com.lgren.util.反射;

import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * TODO
 * @author lgren
 * @create 2019-05-22 15:21
 **/
public class LReflectionUtils {
    public static <T extends Annotation> Method findMethod(Class<?> clazz, String name, Class<T> annotationClass, Class... paramTypes) {
        Method method = ReflectionUtils.findMethod(clazz, name, paramTypes);
        T anno = method.getAnnotation(annotationClass);
        if (anno == null) {
            return null;
        }
        return method;
    }

    public static Field[] findFields(Class<?> clazz) {
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
}
