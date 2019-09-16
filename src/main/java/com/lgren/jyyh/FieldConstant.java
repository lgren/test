package com.lgren.jyyh;

import com.google.common.collect.ImmutableMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/** SC01字段常量 */
public enum FieldConstant {
    SSC008(ImmutableMap.of("1", "文件下载", "2", "搜索", "3", "定制页面")),
    AAE100(ImmutableMap.of("1", "有效", "2", "无效"));
    private Map<String, String> map;
    private List<Map<String, String>> list;

    FieldConstant(Map<String, String> map) {
        this.map = map;
    }

    public String get(String key) {
        return map.get(key);
    }

    public List<Map<String, String>> strList() {
        if (list == null) {
            list = new ArrayList<>(map.size());
            for (Map.Entry<String, String> entry : map.entrySet()) {
                list.add(ImmutableMap.of("id", entry.getKey(), "name", entry.getValue()));
            }
        }
        return list;
    }

    public Map<String, String> strMap() {
        return map;
    }
}
