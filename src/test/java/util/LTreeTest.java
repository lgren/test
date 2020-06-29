package util;

import com.google.common.collect.Lists;
import com.lgren.util.tree.LTreeProcessor;
import lombok.Data;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

/**
 * LTreeUtil 测试
 * @author lgren
 * @since 2019-11-12 16:30
 */
public class LTreeTest {
    List<MyWithChildrenNode> DATA1 = Lists.newArrayList(
            new MyWithChildrenNode(1L, 1L, "节点_1"),

            new MyWithChildrenNode(2L, 1L, "节点_1_2"),
            new MyWithChildrenNode(3L, 1L, "节点_1_3"),

            new MyWithChildrenNode(4L, 2L, "节点_1_2_4"),
            new MyWithChildrenNode(5L, 2L, "节点_1_2_5"),
            new MyWithChildrenNode(6L, 3L, "节点_1_3_6"),

            new MyWithChildrenNode(7L, 4L, "节点_1_2_4_7"),
            new MyWithChildrenNode(8L, 4L, "节点_1_2_4_8")
    );

    @Test
    public void test1() {
        LTreeProcessor<MyWithChildrenNode, Long, MyWithChildrenNode> processor = new LTreeProcessor<>(DATA1, MyWithChildrenNode::getId, MyWithChildrenNode::getPId, o -> o, o -> o);
        List<MyWithChildrenNode> children2 = processor.getChildren(1L);
        List<MyWithChildrenNode> parents = processor.getParents(8L);
        processor.getParents(8L, LTreeProcessor.FEATURE_THIS_DEEP, 1);
        System.out.println();
    }

    @Test
    public void test2() {
        LTreeProcessor<MyWithChildrenNode, Long, LTreeProcessor.Node<MyWithChildrenNode>> tree
                = LTreeProcessor.build(DATA1, MyWithChildrenNode::getId, MyWithChildrenNode::getPId);
        List<MyWithChildrenNode> children2 = tree.getChildren(1L);
        List<MyWithChildrenNode> parents = tree.getParents(8L);
        List<MyWithChildrenNode> allList = tree.treeToList();
        System.out.println();
    }

    @Data
    class MyWithChildrenNode implements LTreeProcessor.INode<MyWithChildrenNode> {
        private Long id;
        private Long pId;
        private String name;

        private Collection<MyWithChildrenNode> children;

        public MyWithChildrenNode(Long id, Long pId, String name) {
            this.id = id;
            this.pId = pId;
            this.name = name;
        }
    }
}
