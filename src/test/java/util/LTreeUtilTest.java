package util;

import com.google.common.collect.Lists;
import com.lgren.util.LTreeUtil;
import com.lgren.util.LgrenUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * LTreeUtil 测试
 * @author lgren
 * @since 2019-11-12 16:30
 */
public class LTreeUtilTest {
    List<Map<String, Object>> DATA = Lists.newArrayList(
            LgrenUtil.newHashMap("id", 1, "pid", 1, "name", "节点_1"),
            LgrenUtil.newHashMap("id", 2, "pid", 1, "name", "节点_1_1"),
            LgrenUtil.newHashMap("id", 3, "pid", 1, "name", "节点_1_2"),
            LgrenUtil.newHashMap("id", 4, "pid", 2, "name", "节点_1_1_1"),
            LgrenUtil.newHashMap("id", 5, "pid", 2, "name", "节点_1_1_2"),
            LgrenUtil.newHashMap("id", 6, "pid", 3, "name", "节点_1_2_1")
    );

    @Test
    public void test() {
        // List<Map<String, Object>> filterList = LTreeUtil.filterList(DataList.DATA, "id", "pid", "children", 3);
        // List<Map<String, Object>> parentList = LTreeUtil.getParents(DataList.DATA, 6, "id", "pid", "parents");

        // 1.排除部分字段 然后将所有的数据转化为树 如果父节点不存在 则子节点也不存在 所以达到移除节点以及其子节点的目的
        List<Map<String, Object>> tree = LTreeUtil.listToTree(DATA, "id", "pid", "children");
        // 2.将树转化为list
        List<Map<String, Object>> result = new ArrayList<>(tree.size());
        // 递归将树转为list
        LTreeUtil.treeToList(result, tree, "children");
        System.out.println();
    }
}
