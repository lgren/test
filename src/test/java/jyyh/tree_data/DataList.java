package jyyh.tree_data;

import com.google.common.collect.Lists;
import com.lgren.util.LgrenUtil;

import java.util.List;
import java.util.Map;

public interface DataList {
    List<Map<String, Object>> DATA = Lists.newArrayList(
            LgrenUtil.newHashMap("id", 1, "pid", 1, "name", "节点_1"),
            LgrenUtil.newHashMap("id", 2, "pid", 1, "name", "节点_1_1"),
            LgrenUtil.newHashMap("id", 3, "pid", 1, "name", "节点_1_2"),
            LgrenUtil.newHashMap("id", 4, "pid", 2, "name", "节点_1_1_1"),
            LgrenUtil.newHashMap("id", 5, "pid", 2, "name", "节点_1_1_2"),
            LgrenUtil.newHashMap("id", 6, "pid", 3, "name", "节点_1_2_1")
    );
}
