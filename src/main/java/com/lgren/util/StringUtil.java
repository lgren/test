package com.lgren.util;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashSet;

/**
 * TODO
 * @author lgren
 * @since 2019-11-01 14:29
 */
public class StringUtil {
    /**
     * 空字符
     */
    public static final String EMPTY = "";
    /**
     * 下划线字符
     */
    public static final char UNDERLINE = '_';

    // 下划线转驼峰
    public static String underlineToCamel(String param) {
        if (isBlank(param)) {
            return EMPTY;
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (c == UNDERLINE) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
    // 其他格式转驼峰
    public static String convertToCamel(String s, char... delimiter) {
        if (s == null || s.length() == 0 || delimiter.length == 0) {
            return s;
        }
        int len = s.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            boolean isContain = false;
            for (char wd : delimiter) {
                if (isContain = (wd == c)) {
                    break;
                }
            }
            if (isContain) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(s.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 判断字符串是否为空
     *
     * @param cs 需要判断字符串
     * @return 判断结果
     */
    public static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String str = "ni_hao-ya-wo_hai-keYiDe";
        System.out.println(StringUtils.abbreviate("like", 4));// 展示l...  必须>=4
        System.out.println(convertToCamel(str, '-', '_'));
        System.out.println();
    }
}
