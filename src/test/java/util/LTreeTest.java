package util;

import com.google.common.collect.Lists;
import com.lgren.util.tree.LTree;
import lombok.Data;
import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LTree 测试
 * @author lgren
 * @since 2019-11-12 16:30
 */
public class LTreeTest {
    @Test
    public void build() {
        LTree<Map<String, Object>, Object, LTree.Node<Map<String, Object>>> lTree
                = LTree.build(DATA_MAP, m -> m.get("id"), m -> m.get("pId")).init();
        List<Map<String, Object>> list1 = lTree.getChildren(1L, LTree.FEATURE_THIS_DEEP, 1);

        List<Map<String, Object>> children2 = lTree.getChildren(1L);
        List<Map<String, Object>> parents = lTree.getParents(8L);
        lTree.getParents(8L, LTree.FEATURE_THIS_DEEP, 1);
        System.out.println();
    }

    @Test
    public void buildUseINode() {
        LTree<ChildrenNode, Long, ChildrenNode> processor
                = LTree.buildUseINode(DATA, ChildrenNode::getId, ChildrenNode::getPId).init();
        List<ChildrenNode> children2 = processor.getChildren(1L);
        List<ChildrenNode> parents = processor.getParents(8L);
        processor.getParents(8L, LTree.FEATURE_THIS_DEEP, 1);
        System.out.println();
    }

    //region 数据
    List<Map<String, Object>> DATA_MAP = Lists.newArrayList(
            getMapNode(1L, 1L, "节点_1"),

            getMapNode(2L, 1L, "节点_1_2"),
            getMapNode(3L, 1L, "节点_1_3"),

            getMapNode(4L, 2L, "节点_1_2_4"),
            getMapNode(5L, 2L, "节点_1_2_5"),
            getMapNode(6L, 3L, "节点_1_3_6"),

            getMapNode(7L, 4L, "节点_1_2_4_7"),
            getMapNode(8L, 4L, "节点_1_2_4_8")
    );
    List<ChildrenNode> DATA = Lists.newArrayList(
            new ChildrenNode(99L, 99L, "节点_1"),

            new ChildrenNode(1L, 1L, "节点_1"),

            new ChildrenNode(2L, 1L, "节点_1_2"),
            new ChildrenNode(3L, 1L, "节点_1_3"),

            new ChildrenNode(4L, 2L, "节点_1_2_4"),
            new ChildrenNode(5L, 2L, "节点_1_2_5"),
            new ChildrenNode(6L, 3L, "节点_1_3_6"),

            new ChildrenNode(7L, 4L, "节点_1_2_4_7"),
            new ChildrenNode(8L, 4L, "节点_1_2_4_8")
    );

    private Map<String, Object> getMapNode(Long id, Long pId, String name) {
        Map<String, Object> map = new HashMap<>(4);
        map.put("id", id);
        map.put("pId", pId);
        map.put("name", name);
        return map;
    }

    @Data
    static class ChildrenNode implements LTree.INode<ChildrenNode> {
        private Long id;
        private Long pId;
        private String name;

        private Collection<ChildrenNode> children;

        public ChildrenNode(Long id, Long pId, String name) {
            this.id = id;
            this.pId = pId;
            this.name = name;
        }
    }
    //endregion

}
