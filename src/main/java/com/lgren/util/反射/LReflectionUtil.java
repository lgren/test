package com.lgren.util.反射;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 反射工具类
 * @author lgren
 * @since 2019-05-22 15:21
 **/
public class LReflectionUtil {
    /**
     * 寻找类下以及子类的所有字段
     * @param clazz 类
     * @return 所有的字段
     */
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

    /**
     * 寻找类下以及子类的所有字段
     * @param clazz 类
     * @param withAnnotation 排除 不带有此注解的字段
     * @param withoutAnnotation 排除 带有此注解排除的字段
     * @return 所有满足的字段
     */
    public static <T extends Annotation> List<Field> findFields(Class<?> clazz, Class<T> withAnnotation, Class<T> withoutAnnotation) {
        List<Field> fieldList = new ArrayList<>(16);
        Field[] fields = clazz.getDeclaredFields();
        hasAnnotation(fields, withAnnotation, withoutAnnotation, fieldList);
        Class<?> tmp = clazz.getSuperclass();
        while (tmp != null) {
            Field[] superFields = tmp.getDeclaredFields();
            hasAnnotation(superFields, withAnnotation, withoutAnnotation, fieldList);
            tmp = tmp.getSuperclass();
        }
        return fieldList;
    }

    /**
     * 寻找类下以及子类的所有字段
     * @param clazz 类
     * @param withAnnotation 排除 不带有此注解的字段
     * @param withoutAnnotation 排除 带有此注解排除的字段
     * @param comparator 排序规则
     * @return 所有满足的字段
     */
    public static <T extends Annotation, E extends Annotation> Set<Field> findFields(Class<?> clazz,
                                                                                     Class<T> withAnnotation,
                                                                                     Class<E> withoutAnnotation,
                                                                                     Comparator<Field> comparator) {
        // 结果list
        Set<Field> fieldList = new TreeSet<>(comparator);
        // 筛选字段
        hasAnnotation(clazz.getDeclaredFields(), withAnnotation, withoutAnnotation, fieldList);
        Class<?> tmp = clazz.getSuperclass();
        // 纵向寻找子类符合天骄的字段
        while (tmp != null) {
            Field[] superFields = tmp.getDeclaredFields();
            hasAnnotation(superFields, withAnnotation, withoutAnnotation, fieldList);
            tmp = tmp.getSuperclass();
        }

        return fieldList;
    }

    /**
     * 将字段数组中满足条件的字段加入到最后结果中
     * @param fields 字段数组 带检查数据
     * @param withAnnotation 排除 不带有此注解的字段
     * @param withoutAnnotation 排除 带有此注解排除的字段
     * @param fieldList 结果list 将满足的字段加入此list中
     */
    private static <T extends Annotation, E extends Annotation> Collection<Field> hasAnnotation(Field[] fields,
                                                                                   Class<T> withAnnotation,
                                                                                   Class<E> withoutAnnotation,
                                                                                   Collection<Field> fieldList) {
        // 如果注解都为空 则直接添加进结果
        if (withAnnotation == null && withoutAnnotation == null) {
            Collections.addAll(fieldList, fields);
        } else {
            for (Field field : fields) {
                // 如果带有包含注解字段 且 不包含有不包含字段 则加入到最后结果中
                if ((withAnnotation == null || field.isAnnotationPresent(withAnnotation))
                        && (withoutAnnotation == null || !field.isAnnotationPresent(withoutAnnotation))) {
                    fieldList.add(field);
                }
            }
        }
        return fieldList;
    }

    public static <T extends Annotation> List<Method> findMethods(Class<?> clazz, Class<T> annotationClass,  String... names) {
        List<Method> methodList = new ArrayList<>(names.length);
        for (String name : names) {
            Method method = ReflectionUtils.findMethod(clazz, name);
            if (method != null) {
                if (annotationClass == null) {
                    methodList.add(method);
                } else {
                    if (method.isAnnotationPresent(annotationClass)) {
                        methodList.add(method);
                    }
                }
            }
        }
        return methodList;
    }

    public static <T extends Annotation> Method findMethod(Class<?> clazz, Class<T> annotationClass,  String name) {
        Method method = ReflectionUtils.findMethod(clazz, name);
        if (method != null) {
            if (annotationClass != null) {
                if (!method.isAnnotationPresent(annotationClass)) {
                    method = null;
                }
            }
        }
        return method;
    }

    /**
     * 获取get方法
     * @param clazz 类
     * @param field 字段
     * @return get方法
     */
    public static Method getGetMethod(Class<?> clazz, Field field) {
        // 获取此字段的 get/is 方法名
        String head;
        if (field.getType() == boolean.class || field.getType() == Boolean.class) {
            if (field.getName().startsWith("is")) {
                return ReflectionUtils.findMethod(clazz, field.getName());
            }
            head = "is";
        } else {
            head = "get";
        }

        return ReflectionUtils.findMethod(clazz, head + StringUtils.capitalize(field.getName()));
    }
}