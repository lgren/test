package jyyh.tree_data;

import com.google.common.collect.Sets;
import com.lgren.util.LTreeUtil;
import com.lgren.util.LgrenUtil;
import org.junit.Test;

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
        // List<Map<String, Object>> tree = LTreeUtil.listToTree(DataList.DATA, "id", "pid", "children", Sets.newHashSet(2));
        // List<Map<String, Object>> tree = LTreeUtil.listToTree(DataList.DATA, "id", "pid", "children", null);

        // List<Map<String, Object>> data1 = LTreeUtil.listAddParents(DataList.DATA, "id", "pid", "parents", null);
        // List<Map<String, Object>> data1 = LTreeUtil.getParents(DataList.DATA, 2, "id", "pid", "parents", null);

        // List<Map<String, Object>> tree = LTreeUtil.listToTree(DataList.DATA, "id", "pid", "children", Sets.newHashSet(2));
        // List<Map<String, Object>> list = LTreeUtil.treeToList(tree, "id", "pid", "children");

        // List<Map<String, Object>> list = LTreeUtil.filterList(DataList.DATA, "id", "pid", "children", Sets.newHashSet(4));

        List<Map<String, Object>> tree = LTreeUtil.listToTree(DataList.DATA, "id", "pid", "children");
        List<Map<String, Object>> data1 = LTreeUtil.getParents(DataList.DATA, 6, "id", "pid", "parents");
        System.out.println();
    }
}
