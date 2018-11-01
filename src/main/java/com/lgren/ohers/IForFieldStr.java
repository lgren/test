package com.lgren.ohers;

import java.util.List;
import java.util.Map;

public interface IForFieldStr {
    Map<Integer, String> strMap = null;
    List<Map<String, Object>> fieldList = null;
    /**
     * 获取枚举值中的 Map
     * @create 2018/8/30 9:03
     * @author Lgren
     * @return 枚举值中的 Map
     */
    static Map<Integer, String> getStrMap() {
        return strMap;
    }

    /**
     * 将枚举值中的 Map转换成对应前端展示字段
     * @create 2018/8/30 9:04
     * @author Lgren
     * @return 前端展示字段的 List<Map<String, Object>>
     */
    static List<Map<String, Object>> getFieldList() {
        return fieldList;
    }

    /**
     * 获取枚举值中 Map的值
     * @create 2018/8/30 9:07
     * @author Lgren
     * @param key 枚举值中 Map的 key
     * @return  枚举值中 Map的 value
     */
    static String get(Integer key) {
        return strMap.get(key);
    }
}
