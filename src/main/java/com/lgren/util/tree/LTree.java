package com.lgren.util.tree;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.list.TreeList;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * 树的工具类
 * @param <T> 每个节点的类型
 * @param <K> ID和PID的类型
 * @author Lgren
 * @since 2020/1/3 15:56
 */
public class LTree<T, K, N> {
    // 特征 默认
    public static final int FEATURE_DEFAULT = 0;
    // 特征 只需要此层
    public static final int FEATURE_THIS_DEEP = 0x0000_0001;
    // 特征 是否带有自身节点
    public static final int FEATURE_WITH_SELF = 0x0000_0010;
    // 需要寻找的深度
    public static final int DEEP_ALL = 0;
    /*-------------------------------------------------- 非必设置 --------------------------------------------------*/
    @Getter
    @Setter
    private Predicate<K> pIdNotFindNode;// 未找到父节点的节点处理方法

    /*-------------------------------------------------- 必须设置 --------------------------------------------------*/
    @Getter
    private Collection<T> data;// 源数据
    private Function<T, K> getId;// 获取ID的方法
    private Function<T, K> getPId;// 获取P_ID的方法
    private Function<N, Collection<N>> getChildren;// 获取子节点集合的方法
    private BiConsumer<N, Collection<N>> setChildren;// 设置子节点集合的方法
    private Function<T, N> oriToNode;// 源文件转节点的方法
    private Function<N, T> nodeGetOri;// 节点获取源文件的方法

    /*-------------------------------------------------- 内部数据 --------------------------------------------------*/
    private Map<K, T> sourceMap;// ID作为KEY 解析后的所有节点
    private Map<K, N> nodeMap;// ID作为KEY 解析后的所有节点
    private Map<K, N> nodeTree;// ID作为KEY 解析后的所有节点


    /**
     * 获取一个解析树对象
     * @param sourceColl  源数据
     * @param getId       获取ID的方法
     * @param getPId      获取P_ID的方法
     * @param getChildren 获取子节点集合的方法
     * @param setChildren 设置子节点集合的方法
     * @param oriToNode   源文件转节点的方法
     * @param nodeGetOri  节点获取源文件的方法
     */
    public LTree(Collection<T> sourceColl, Function<T, K> getId, Function<T, K> getPId,
                 Function<N, Collection<N>> getChildren, BiConsumer<N, Collection<N>> setChildren,
                 Function<T, N> oriToNode, Function<N, T> nodeGetOri) {
        this.data = Objects.requireNonNull(sourceColl, "数据源不能为空!");
        this.getId = Objects.requireNonNull(getId, "获取ID的方法不能为空!");
        this.getPId = Objects.requireNonNull(getPId, "获取父ID的方法不能为空!");
        this.oriToNode = Objects.requireNonNull(oriToNode, "源文件转节点方法不能为空!");
        this.nodeGetOri = Objects.requireNonNull(nodeGetOri, "节点获取源文件方法不能为空!");
        this.getChildren = Objects.requireNonNull(getChildren, "获取子节点方法不能为空!");
        this.setChildren = Objects.requireNonNull(setChildren, "设置子节点方法不能为空!");
    }

    /**
     * 解析树
     */
    public LTree<T, K, N> init() {
        // 将原数据放入一个Map中
        int capacity = capacity(this.data.size());
        this.sourceMap = new HashMap<>(capacity);
        this.nodeMap = new HashMap<>(capacity);
        for (T d : this.data) {
            K id = Objects.requireNonNull(this.getId.apply(d), "树ID不能为空");// 获取节点ID
            this.sourceMap.put(id, d);
            this.nodeMap.put(id, this.oriToNode.apply(d));
        }

        // 数据ID都不能为空 空间大小不确定 所以默认
        this.nodeTree = new LinkedHashMap<>();
        for (T sourceItem : this.data) {
            K id = this.getId.apply(sourceItem);// 获取节点ID 之前空值判断过 所以此处都不为空的
            N node = this.nodeMap.get(id);// 获取当前节点
            K pid = this.getPId.apply(sourceItem);// 获取父节点ID

            // 是根节点 pid为空 或 自身id和pid相同
            if (pid == null || Objects.equals(id, pid)) {
                this.nodeTree.put(id, node);
            } else {// 是子节点
                N pNode = this.nodeMap.get(pid);// 获取父节点
                // 如果父节点不存在 且 (处理方法为空 或者 处理方法返回值为false) 则 跳过此节点不予处理
                if (pNode == null && (pIdNotFindNode == null || !pIdNotFindNode.test(pid)))
                    continue;
                Collection<N> pChildren = this.getChildren.apply(pNode);
                // 获取父节点的子节点集合 如果为空则初始化
                if (pChildren == null) {
                    pChildren = new ArrayList<>();// 默认大小
                    this.setChildren.accept(pNode, pChildren);
                }
                pChildren.add(node);
            }
        }
        return this;
    }

    /**
     * 获取一个解析树对象
     * @param sourceColl 源数据
     * @param getId      获取ID的方法
     * @param getPId     获取PID的方法
     */
    public static <T, K> LTree<T, K, Node<T>> build(Collection<T> sourceColl, Function<T, K> getId, Function<T, K> getPId) {
        return new LTree<>(sourceColl, getId, getPId, Node::getChildren, Node::setChildren, Node::new, Node::getSource);
    }

    /**
     * 获取一个解析树对象
     * @param sourceColl 源数据
     * @param getId      获取ID的方法
     * @param getPId     获取PID的方法
     */
    public static <T extends INode<T>, K> LTree<T, K, T> buildUseINode(Collection<T> sourceColl, Function<T, K> getId, Function<T, K> getPId) {
        return new LTree<>(sourceColl, getId, getPId, T::getChildren, T::setChildren, o -> o, o -> o);
    }

    public T get(K id) {
        return sourceMap.get(id);
    }

    public N getNode(K id) {
        return nodeMap.get(id);
    }

    // public <R> List<R> filterBase(K id, Function<T, R> returnConvert) {
    //     N thisNode = getNode(id);
    //     Collection<N> children = thisNode.getChildren();
    //     treeToListBase(children, FEATURE_DEFAULT, DEEP_ALL, nodeGetOri);
    //
    // }

    //region 获取子节点
    public List<T> getChildren(K id) {
        return getChildren(id, FEATURE_DEFAULT, DEEP_ALL);
    }

    public List<T> getChildren(K id, int feature, int deep) {
        return getChildrenBase(id, feature, deep, o -> nodeGetOri.apply(o));
    }

    public List<K> getChildrenId(K id) {
        return getChildrenId(id, FEATURE_DEFAULT, DEEP_ALL);
    }

    public List<K> getChildrenId(K id, int feature, int deep) {
        return getChildrenBase(id, feature, deep, o -> getId.apply(nodeGetOri.apply(o)));
    }

    public List<N> getChildrenNode(K id) {
        return getChildrenNode(id, FEATURE_DEFAULT, DEEP_ALL);
    }

    public List<N> getChildrenNode(K id, int feature, int deep) {
        return getChildrenBase(id, feature, deep, o -> o);
    }

    private <R> List<R> getChildrenBase(K id, int feature, int deep, Function<N, R> returnConvert) {
        Collection<N> children;
        List<R> result = new ArrayList<>();// 默认大小
        if (id == null) {
            children = nodeTree.values();
        } else {
            N node = nodeMap.get(id);
            if (node == null) {
                return Collections.emptyList();
            }
            // 带有自身节点
            if (((feature & FEATURE_WITH_SELF) != 0)) {
                result.add(returnConvert.apply(node));
            }
            children = getChildren.apply(node);
        }
        getChildrenBaseCallback(result, children, feature, deep, returnConvert, 1);
        return result;
    }

    private <R, C extends Collection<R>> void getChildrenBaseCallback(C result, Collection<N> nodeColl, int feature, int deep, Function<N, R> returnConvert, int nowDeep) {
        if ((deep > 0 && nowDeep > deep) || nodeColl == null || nodeColl.isEmpty()) {
            return;
        }
        for (N node : nodeColl) {
            // 不用只需要指定的这一层
            if ((feature & FEATURE_THIS_DEEP) == 0) {
                result.add(returnConvert.apply(node));
                // 只需要指定的这一层
            } else if (deep == nowDeep) {
                result.add(returnConvert.apply(node));
                continue;
            }
            getChildrenBaseCallback(result, getChildren.apply(node), feature, deep, returnConvert, nowDeep + 1);
        }
    }
    //endregion

    //region 获取父
    public List<T> getParents(K id) {
        return getParents(id, FEATURE_DEFAULT, DEEP_ALL);
    }

    public List<K> getParentsId(K id) {
        return getParentsId(id, FEATURE_DEFAULT, DEEP_ALL);
    }

    public List<N> getParentsNode(K id) {
        return getParentsNode(id, FEATURE_DEFAULT, DEEP_ALL);
    }

    public List<T> getParents(K id, int feature, int deep) {
        return getParentsBase(id, feature, deep, o -> o);
    }

    public List<K> getParentsId(K id, int feature, int deep) {
        return getParentsBase(id, feature, deep, getPId);
    }

    public List<N> getParentsNode(K id, int feature, int deep) {
        return getParentsBase(id, feature, deep, oriToNode);
    }

    private <R> List<R> getParentsBase(K id, int feature, int deep, Function<T, R> returnConvert) {
        List<R> result = new ArrayList<>(6);
        int nowDeep = 0;
        T source = get(id);
        if ((feature & FEATURE_WITH_SELF) != 0) {
            result.add(returnConvert.apply(source));
        }
        // 获取父节点id
        K pid = getPId.apply(source);
        // 获取父节点
        T parentSource;
        // 当父节点不为空
        while ((deep < 1 || deep > nowDeep) && pid != null && !Objects.equals(id, pid) && (parentSource = sourceMap.get(pid)) != null) {
            // 不用只需要指定的这一层
            if ((feature & FEATURE_THIS_DEEP) == 0) {
                result.add(returnConvert.apply(parentSource));
                // 只需要指定的这一层
            } else if (deep == nowDeep) {
                result.add(returnConvert.apply(parentSource));
                break;
            }
            nowDeep++;
            // 节点获取源文件
            source = parentSource;
            // 获取当前节点id
            id = getId.apply(source);
            // 获取父节点id
            pid = getPId.apply(source);
        }
        return result;
    }
    //endregion

    //region 树转List
    public List<T> treeToList() {
        return treeToList(FEATURE_DEFAULT, DEEP_ALL);
    }

    public List<K> treeToIdList() {
        return treeToIdList(FEATURE_DEFAULT, DEEP_ALL);
    }

    public List<N> treeToNodeList() {
        return treeToNodeList(FEATURE_DEFAULT, DEEP_ALL);
    }

    public List<T> treeToList(int feature, int deep) {
        return treeToListBase(feature, deep, nodeGetOri);
    }

    public List<K> treeToIdList(int feature, int deep) {
        return treeToListBase(feature, deep, o -> getId.apply(nodeGetOri.apply(o)));
    }

    public List<N> treeToNodeList(int feature, int deep) {
        return treeToListBase(feature, deep, o -> o);
    }

    private <R> List<R> treeToListBase(int feature, int deep, Function<N, R> returnConvert) {
        return treeToListBase(nodeTree.values(), feature, deep, returnConvert);
    }

    private <R> List<R> treeToListBase(Collection<N> data, int feature, int deep, Function<N, R> returnConvert) {
        List<R> result = new ArrayList<>(22);
        int nowDeep = 1;
        List<Collection<N>> tmpNodeChildrenCollList = new LinkedList<>();
        List<Collection<N>> tmpNodeCollList = new LinkedList<>();
        tmpNodeCollList.add(data);
        // 是否结束循环
        boolean isBreak;
        while ((deep < 1 || deep >= nowDeep) && !tmpNodeCollList.isEmpty()) {
            tmpNodeChildrenCollList.clear();
            isBreak = false;
            for (Collection<N> nodeColl : tmpNodeCollList) {
                for (N node : nodeColl) {
                    if ((feature & FEATURE_THIS_DEEP) == 0 || deep == nowDeep) {
                        // 只需要当前深度 且 达到了当前深度
                        if ((feature & FEATURE_THIS_DEEP) != 0) {
                            result.add(returnConvert.apply(node));
                            isBreak = true;
                            // 不仅仅需要当前深度
                        } else {
                            result.add(returnConvert.apply(node));
                            Optional.ofNullable(getChildren.apply(node)).filter(c -> !c.isEmpty()).ifPresent(tmpNodeChildrenCollList::add);
                        }
                        // 只需要当前深度 且 未达到当前深度
                    } else {
                        Optional.ofNullable(getChildren.apply(node)).filter(c -> !c.isEmpty()).ifPresent(tmpNodeChildrenCollList::add);
                    }
                }
            }
            if (isBreak) {
                break;
            }
            tmpNodeCollList.clear();
            tmpNodeCollList.addAll(tmpNodeChildrenCollList);
            nowDeep++;
        }
        return result;
    }
    //endregion

    @Data
    public static class Node<T> implements INode<Node<T>> {
        private T source;
        private Collection<Node<T>> children;

        public void setChildren(Collection<Node<T>> children) {
            this.children = children;
        }

        public Node(T source) {
            this.source = source;
        }
    }

    public interface INode<This> {
        Collection<This> getChildren();

        void setChildren(Collection<This> children);

        default void addChild(This node) {
            if (getChildren() == null) {
                setChildren(new ArrayList<>(22));
            }
            getChildren().add(node);
        }
    }

    private int capacity(int expectedSize) {
        if (expectedSize < 0) {
            throw new IllegalArgumentException("容量值不能小于0");
        }
        if (expectedSize < 3) {
            return expectedSize + 1;
        }
        return expectedSize < 1073741824 ? (int) ((double) expectedSize / 0.75F + 1.0F) : 2147483647;
    }
}
