package com.lgren.jyyh_jsp.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

/**
 * TODO
 * @author lgren
 * @create 2019-06-11 16:42
 **/
public class LgrenUtil {
    public static <T> Optional<T> notBlank(T obj) {
        if (obj != null && obj.getClass() == String.class && StringUtils.isBlank((String) obj)) {
            return Optional.empty();
        }
        return Optional.ofNullable(obj);
    }
}
