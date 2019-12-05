package com.lgren.util;

import com.lgren.util.反射.LReflectionUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cglib.beans.BeanMap;

import javax.sql.rowset.serial.SerialBlob;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 针对数据库字段blob进行处理的工具类
 * @author lgren
 * @since 2019-11-07 14:30
 */
public class BlobUtil {
    /**
     * 将列表的Blob转换成String
     * @param list           源数据List
     * @param blobFieldNames 需要转换的字段名
     * @return 传入的分页bean
     */
    @SuppressWarnings("unchecked")
    public static List<Map<String, Object>> handlerBlob(List<Map<String, Object>> list, String... blobFieldNames) {
        if (list == null || list.size() == 0) {
            return list;
        }
        for (Map<String, Object> map : list) {
            for (String blobFieldName : blobFieldNames) {
                Blob blob = (Blob) map.get(blobFieldName);
                map.put(blobFieldName, blobToString(blob));
            }
        }
        return list;
    }

    /**
     * 将列表的Blob转换成String
     * @param list 源数据List
     * @return 传入的分页bean
     */
    public static List<Map<String, Object>> handlerByteArrForDomain(List<HasToMapMethod> list) {
        if (list == null) {
            return null;
        }
        if (list.size() == 0) {
            return new ArrayList<>(0);
        }
        Class<? extends HasToMapMethod> thisClass = list.get(0).getClass();
        // 获取带有 BlobToString注解的字段
        List<Field> fields = LReflectionUtil.findFields(thisClass, BlobToString.class, null);
        // 将注解value作为key get方法作为value放入map中
        Map<String, Method> methodMap = new HashMap<>(fields.size());
        for (Field field : fields) {
            String strName = Optional.of(field.getAnnotation(BlobToString.class).value())
                    .filter(StringUtils::isNotBlank)
                    .orElse(field.getName());
            methodMap.put(strName, LReflectionUtil.getGetMethod(thisClass, field));
        }
        // 循环数据列表
        List<Map<String, Object>> resultList = list.stream().map(o -> {
            Map<String, Object> objMap = o.toMap();
            methodMap.forEach((k, v) -> {
                try {
                    // 通过get方法获取值
                    byte[] value = (byte[]) v.invoke(o);
                    objMap.put(k, byteArrToString(value));
                } catch (IllegalAccessException | InvocationTargetException | UnsupportedEncodingException e) {
                    System.out.println("list byte[]转String异常");
                }
            });
            return objMap;
        }).collect(Collectors.toList());
        return resultList;
    }

    /**
     * 将列表的byte[]转换成String
     * @param entity 实体类
     * @return 转换后的map
     */
    public static Map<String, Object> handlerByteArrForDomain(HasToMapMethod entity) {
        Class<? extends HasToMapMethod> thisClass = entity.getClass();
        // 获取带有 BlobToString注解的字段
        List<Field> fields = LReflectionUtil.findFields(thisClass, BlobToString.class, null);
        // 将注解value作为key get方法作为value放入map中
        Map<String, Method> methodMap = new HashMap<>(fields.size());
        for (Field field : fields) {
            String strName = Optional.of(field.getAnnotation(BlobToString.class).value())
                    .filter(StringUtils::isNotBlank)
                    .orElse(field.getName());
            methodMap.put(strName, LReflectionUtil.getGetMethod(thisClass, field));
        }
        Map<String, Object> objMap = entity.toMap();
        methodMap.forEach((name, method) -> {
            try {
                // 通过get方法获取值
                byte[] value = (byte[]) method.invoke(entity);
                objMap.put(name, byteArrToString(value));
            } catch (IllegalAccessException | InvocationTargetException | UnsupportedEncodingException e) {
                System.out.println("byte[]转String异常");
            }
        });
        return objMap;
    }

    /** 将String转换成byte数组 */
    public static byte[] stringToByteArr(String str) throws UnsupportedEncodingException {
        if (str == null) {
            return null;
        }
        return str.getBytes("UTF-8");
    }

    /** byte数组 转换成 String */
    public static String byteArrToString(byte[] byteArr) throws UnsupportedEncodingException {
        if (byteArr == null) {
            return null;
        }
        return new String(byteArr, "UTF-8");
    }

    /** String 转换成 Blob */
    public static Blob stringToBlob(String string) throws UnsupportedEncodingException, SQLException {
        if (string == null) {
            return null;
        }
        return new SerialBlob(string.getBytes("UTF-8"));
    }

    /** Blob 转换成 String */
    public static String blobToString(Blob blob) {
        if (blob == null) {
            return null;
        }
        String blobString = null;//blob 转 String
        try {
            blobString = new String(blob.getBytes(1L, (int) blob.length()), "UTF-8");
        } catch (UnsupportedEncodingException | SQLException e) {
            e.printStackTrace();
        }
        return blobString;
    }

    /** 此注解代表方法具有toMap()方法 */
    public interface HasToMapMethod extends Serializable {
        @SuppressWarnings("unchecked")
        default Map<String, Object> toMap() {
            return new HashMap<>(BeanMap.create(this));
        }
    }

    /** 此注解为了将 byte[] 转换成 String 不过需要使用此工具类的 {@link BlobUtil#handlerByteArrForDomain(List<HasToMapMethod>)}关联使用 */
    @Target({ElementType.METHOD, ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface BlobToString {
        String value() default "";
    }
}
