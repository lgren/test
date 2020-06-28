import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.http.HttpUtil;
import org.junit.Test;

import java.util.List;

/**
 * TODO
 * @author lgren
 * @since 2020-06-19 4:06 下午
 */
public class HuToolTest {
    @Test
    public void name() {
        // 构建node列表
        List<TreeNode<String>> nodeList = CollUtil.newArrayList();

        nodeList.add(new TreeNode<>("1", "0", "系统管理", 5));
        nodeList.add(new TreeNode<>("11", "1", "用户管理", 222222));
        nodeList.add(new TreeNode<>("111", "11", "用户添加", 0));
        nodeList.add(new TreeNode<>("2", "0", "店铺管理", 1));
        nodeList.add(new TreeNode<>("21", "2", "商品管理", 44));
        nodeList.add(new TreeNode<>("221", "2", "商品管理2", 2));

        // 0表示最顶层的id是0
        List<Tree<String>> treeList = TreeUtil.build(nodeList, "0");
        System.out.println();

    }

    @Test
    public void console() {
        Console.log("你好呀");
    }

    @Test
    public void httpGet() {
        String result = HttpUtil.get("http://192.168.17.100:8097/iApp2/answer/api/question.do?question=就业&questionFlag=2&questionLevel=1&channel=3&systemId=100000000000000006");
        System.out.println(result);
    }
}
