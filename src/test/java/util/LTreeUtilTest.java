package util;

import com.google.common.collect.Lists;
import com.lgren.util.LTreeUtil;
import com.lgren.util.LgrenUtil;
import com.lgren.util.tree.LTreeProcessor;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
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

    List<MyNode> DATA2 = Lists.newArrayList(
            new MyNode(1L, 1L, "节点_1"),

            new MyNode(2L, 1L, "节点_1_1"),
            new MyNode(3L, 1L, "节点_1_2"),

            new MyNode(4L, 2L, "节点_1_1_1"),
            new MyNode(5L, 2L, "节点_1_1_2"),
            new MyNode(6L, 3L, "节点_1_2_1")
    );

    @Test
    public void test2() {
        com.lgren.util.LTreeProcessor lTreeProcessor = com.lgren.util.LTreeProcessor.get(DATA2, MyNode::getId, MyNode::getPId);
        List<MyNode> children = lTreeProcessor.getChildren(2L);
        List<MyNode> parents = lTreeProcessor.getParents(2L);
        List<MyNode> levelList = lTreeProcessor.getByMaxLevel(1, 1L);
        List<MyNode> onlyLevelList = lTreeProcessor.getByOnlyLevel(1, 1L);
        System.out.println();
    }

    List<MyWithChildrenNode3> DATA3 = Lists.newArrayList(
            new MyWithChildrenNode3(1L, 1L, "节点_1"),

            new MyWithChildrenNode3(2L, 1L, "节点_1_1"),
            new MyWithChildrenNode3(3L, 1L, "节点_1_2"),

            new MyWithChildrenNode3(4L, 2L, "节点_1_1_1"),
            new MyWithChildrenNode3(5L, 2L, "节点_1_1_2"),
            new MyWithChildrenNode3(6L, 3L, "节点_1_2_1")
    );

    @Test
    public void test5() {
        LTreeProcessor<MyWithChildrenNode3, Long, MyWithChildrenNode3> processor = LTreeProcessor.get(DATA3, MyWithChildrenNode3::getId, MyWithChildrenNode3::getPId, (o) -> o);
        List<MyWithChildrenNode3> children = processor.getChildren(2L);
        List<MyWithChildrenNode3> parents = processor.getParents(2L);
        List<MyWithChildrenNode3> levelList = processor.getByMaxLevel(1, 1L);
        List<MyWithChildrenNode3> onlyLevelList = processor.getByOnlyLevel(1, 1L);
        System.out.println();
    }

    @Data
    @AllArgsConstructor
    class MyNode {
        private Long id;
        private Long pId;
        private String name;
    }

    @Data
    class MyWithChildrenNode3 implements LTreeProcessor._INode<MyWithChildrenNode3> {
        private Long id;
        private Long pId;
        private String name;

        private Collection<LTreeProcessor._INode<MyWithChildrenNode3>> children;

        public MyWithChildrenNode3(Long id, Long pId, String name) {
            this.id = id;
            this.pId = pId;
            this.name = name;
        }

        @Override
        public MyWithChildrenNode3 getObj() {
            return this;
        }
    }
}
