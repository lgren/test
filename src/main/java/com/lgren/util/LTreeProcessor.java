package com.lgren.util;

import lombok.Data;
import lombok.Getter;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * 树的工具类
 * @param <T> 每个节点的类型
 * @param <K> ID和PID的类型
 * @since 2020/1/3 15:56
 * @author Lgren
 */
public class LTreeProcessor<T, K> {
    @Getter
    private Collection<T> data;// 源数据
    private Function<T, K> getId;// 获取ID的方法
    private Function<T, K> getPId;// 获取PID的方法
    private Map<K, Node> tmpDataMap;// ID作为KEY 解析后的所有节点
    @Getter
    private Map<K, Node> tmpDataTree;// ID作为KEY 解析后的所有节点

    //region 获取解析树对象
    /**
     * 获取一个解析树对象
     * @param data 源数据
     * @param getId 获取ID的方法
     * @param getPId 获取PID的方法
     */
    private LTreeProcessor(Collection<T> data, Function<T, K> getId, Function<T, K> getPId) {
        Objects.requireNonNull(data, "数据源不能为空!");
        Objects.requireNonNull(getId, "获取ID的方法不能为空!");
        Objects.requireNonNull(getPId, "获取父ID的方法不能为空!");

        this.data = data;
        this.getId = getId;
        this.getPId = getPId;
        // 将原数据放入一个Map中
        Map<K, Node> tmpMap = data.stream().filter(Objects::nonNull).collect(Collectors.toMap(getId, Node::new, (oldMap, newMap) -> oldMap));
        this.tmpDataTree = new HashMap<>(16);
        for (T oriNode : data) {
            K id = getId.apply(oriNode);// 获取当前节点ID
            Objects.requireNonNull(id, "树ID不能为空");
            Node node = tmpMap.get(id);
            K pid = getPId.apply(oriNode);// 获取当前节点父节点
            Node pNode = tmpMap.get(pid);// 获取父节点
            //tmpMap存储的均为id为key的键值对，如果以pid为key可以取出对象，则表明该元素是父级元素
            if (pNode != null && !Objects.equals(id, pid)) {
                if (pNode.getChildren() == null) {
                    pNode.setChildren(new ArrayList<>());
                }
                //给当前这个父级map对象中添加key为children的ArrayList
                Collection<Node> children = pNode.getChildren();
                children.add(node);
            } else {
                this.tmpDataTree.put(id, node);
            }
        }
        this.tmpDataMap = tmpMap;
    }

    /**
     * 获取一个解析树对象
     * @param data 源数据
     * @param getId 获取ID的方法
     * @param getPId 获取PID的方法
     * @param <T> 每个节点的类型
     * @param <K> ID和PID的类型
     * @return 解析树对象
     */
    public static <T, K> LTreeProcessor<T, K> get(Collection<T> data, Function<T, K> getId, Function<T, K> getPId) {
        return new LTreeProcessor<>(data, getId, getPId);
    }
    //endregion

    //region 查询所有父节点
    /**
     * 获取此ID下的包含自己的所有子节点
     * @param id 节点ID
     * @return 节点列表
     */
    public List<T> getParents(K id) {
        Node node = tmpDataMap.get(id);
        if (node == null) {
            return Collections.emptyList();
        }
        List<T> result = new ArrayList<>(4);
        return getParentsBase(result, node, o -> o);
    }

    /**
     * 获取此ID下的包含自己的所有子节点的ID
     * @param id 节点ID
     * @return 节点ID列表
     */
    public List<K> getParentsId(K id) {
        Node node = tmpDataMap.get(id);
        if (node == null) {
            return Collections.emptyList();
        }
        List<K> result = new ArrayList<>(4);
        return getParentsBase(result, node, getId);
    }

    /**
     * 获取此ID下的包含自己的所有子节点
     * @param result 结果集
     * @param node 需要查询的节点
     * @param returnFunc 返回列表的类型方法
     * @param <RT> 返回列表的类型
     * @param <R> 返回类型
     * @return 返回 result
     */
    private <RT, R extends Collection<RT>> R getParentsBase(R result, Node node, Function<T, RT> returnFunc) {
        Node tmpNode = node;
        T t;
        K k, pk;
        do {
            t = tmpNode.getObj();
            k = getId.apply(t);
            result.add(returnFunc.apply(t));
            pk = getPId.apply(t);
            tmpNode = Objects.equals(k, pk) ? null : tmpDataMap.get(pk);
        } while (tmpNode != null);
        return result;
    }
    //endregion

    //region 查询所有子节点
    /**
     * 获取此ID下的包含自己的所有子节点的
     * @param ids 节点ID数组
     * @return 节点列表
     */
    public List<T> getChildren(K... ids) {
        return getChildren(ArrayList::new, o -> o, ids);
    }

    /**
     * 获取此ID下的包含自己的所有子节点的ID
     * @param ids 节点ID数组
     * @return 节点ID列表
     */
    public List<K> getChildrenId(K... ids) {
        return getChildren(ArrayList::new, getId, ids);
    }

    /**
     * 获取此ID下的包含自己的所有父节点的
     * @param ids 节点ID数组
     * @return 节点ID列表
     */
    public <RT, R extends Collection<RT>> R getChildren(Supplier<R> resultTypeFunc, Function<T, RT> returnFunc, K... ids) {
        if (ids.length == 0) {
            return resultTypeFunc.get();
        }
        Set<Node> nodeSet = new LinkedHashSet<>(ids.length);
        for (K id : ids) {
            nodeSet.add(tmpDataMap.get(id));
        }
        return getChildrenBase(resultTypeFunc.get(), nodeSet, returnFunc, -1, 0, false);
    }

    /**
     * 获取此ID下的包含自己的所有子节点的 会有层数限制
     * @param maxLevel 最多到多少层 0开始计数
     * @param ids 节点ID数组
     * @return 节点列表
     */
    public List<T> getByMaxLevel(int maxLevel, K... ids) {
        return getChildrenByLevel(ArrayList::new, o -> o, maxLevel, false, ids);
    }

    /**
     * 获取此ID下的包含自己的所有子节点的 会有层数限制
     * @param onlyLevel 只要多少层 0开始计数
     * @param ids 节点ID数组
     * @return 节点列表
     */
    public List<T> getByOnlyLevel(int onlyLevel, K... ids) {
        return getChildrenByLevel(ArrayList::new, o -> o, onlyLevel, true, ids);
    }

    /**
     * 获取此ID下的包含自己的所有父节点的
     * @param ids 节点ID数组
     * @return 节点ID列表
     */
    public <RT, R extends Collection<RT>> R getChildrenByLevel(Supplier<R> resultTypeFunc, Function<T, RT> returnFunc, int level, boolean isOnlyLevel, K... ids) {
        Set<Node> nodeSet = new LinkedHashSet<>(ids.length);
        if (ids.length == 0) {
            tmpDataTree.forEach((id, v) -> nodeSet.add(v));
        } else {
            for (K id : ids) {
                nodeSet.add(tmpDataMap.get(id));
            }
        }
        return getChildrenBase(resultTypeFunc.get(), nodeSet, returnFunc, level, 0, isOnlyLevel);
    }

    /**
     * 获取此ID下的包含自己的所有父节点
     * @param result 结果集
     * @param nodeColl 需要查询的节点集合
     * @param returnFunc 返回列表的类型方法
     * @param level 只显示某一层
     * @param nowLevel 回调用的当前层数
     * @param <RT> 返回列表的类型
     * @param <R> 返回类型
     * @return 返回 result
     */
    private <RT, R extends Collection<RT>> R getChildrenBase(R result, Collection<Node> nodeColl, Function<T, RT> returnFunc, int level, int nowLevel, boolean isOnlyLevel) {
        Objects.requireNonNull(result, "结果集不能为空!");
        if (level > -1 && nowLevel > level) {
            return result;
        }
        if (nodeColl == null || nodeColl.isEmpty()) {
            return result;
        }
        for (Node node : nodeColl) {
            if (!isOnlyLevel || Objects.equals(nowLevel, level)) {
                result.add(returnFunc.apply(node.getObj()));
            }
            getChildrenBase(result, node.getChildren(), returnFunc, level, nowLevel + 1, isOnlyLevel);
        }
        return result;
    }
    //endregion

    @Data
    private class Node {
        private T obj;

        private Collection<Node> children;

        public Node(T obj) {
            this.obj = obj;
        }
    }
}
