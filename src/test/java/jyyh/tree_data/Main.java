package jyyh.tree_data;

import com.google.common.collect.Sets;
import com.lgren.util.LTreeUtil;
import com.lgren.util.LgrenUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * TODO
 * @author lgren
 * @since 2019-08-26 15:02
 */
public class Main {
    @Test
    public void test() {
        // List<Map<String, Object>> filterList = LTreeUtil.filterList(DataList.DATA, "id", "pid", "children", 3);
        // List<Map<String, Object>> parentList = LTreeUtil.getParents(DataList.DATA, 6, "id", "pid", "parents");

        // 1.排除部分字段 然后将所有的数据转化为树 如果父节点不存在 则子节点也不存在 所以达到移除节点以及其子节点的目的
        List<Map<String, Object>> tree = LTreeUtil.listToTree(DataList.DATA, "id", "pid", "children");
        // 2.将树转化为list
        List<Map<String, Object>> result = new ArrayList<>(tree.size());
        // 递归将树转为list
        LTreeUtil.treeToList(result, tree, "children");
        System.out.println();
    }
}
