package com.lgren.util;

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
        String str = "niHaoYa_test";
        System.out.println(underlineToCamel(str));
        System.out.println();
    }
}
