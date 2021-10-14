package frame.hutool;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.XmlUtil;
import cn.hutool.cron.CronUtil;
import cn.hutool.http.HtmlUtil;
import cn.hutool.setting.dialect.Props;
import org.junit.Test;
import org.springframework.cglib.beans.BeanMap;
import pojo.Person;

import java.util.Date;
import java.util.List;

/**
 * TODO
 * @author lgren
 * @since 2020-06-19 4:06 下午
 */
public class Base1Test {

    @Test
    public void tree() {
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
        Console.log("你好呀 {}", "小伙子");
    }

    @Test
    public void exception() {
        ExceptionUtil.wrapRuntime(new RuntimeException());
    }

    @Test
    public void thread() {
    }

    @Test
    public void properties() {
        Props props = new Props("test.properties");
        String user = props.getProperty("user");
        String driver = props.getStr("driver");
    }

    @Test
    public void html() {
        HtmlUtil.escape("");
    }

    @Test
    public void cron() {
        CronUtil.start();
    }

    @Test
    public void xmlCovert() {
        // String str = XmlUtil.mapToXmlStr(MapUtil.builder().put("code", "01").put("test", ListUtil.of(
        //         BeanUtil.beanToMap(new Person(23123L, "01", new Date())),
        //         BeanUtil.beanToMap(new Person(231231L, "02", new Date()))
        // )).build());
        // System.out.println(str);

        String str = XmlUtil.mapToXmlStr(MapUtil.builder().put("code", "01").put("test", ListUtil.of(
                BeanMap.create(new Person(23123L, "01", new Date())),
                BeanMap.create(new Person(231231L, "02", new Date()))
        )).build());
        System.out.println(str);
    }
}
