package com.lgren.util.tree;

import com.lgren.util.LgrenUtil;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 树的工具类
 * @param <T> 每个节点的类型
 * @param <K> ID和PID的类型
 * @author Lgren
 * @since 2020/1/3 15:56
 */
public class LTreeProcessor<T, K, N extends LTreeProcessor.INode<N>> {
    // 特征 默认
    public static final int FEATURE_DEFAULT = 0;
    // 特征 只需要此层
    public static final int FEATURE_THIS_DEEP = 0x0000_0001;
    // 需要寻找的深度
    public static final int DEEP_ALL = 0;
    @Getter
    @Setter
    private Consumer<K> pIdNotFindNode;
    @Getter
    private Collection<T> data;// 源数据
    private Function<T, K> getId;// 获取ID的方法
    private Function<T, K> getPId;// 获取PID的方法

    private Function<T, N> oriToNode;// 源文件转节点方法
    private Function<N, T> nodeGetOri;// 节点获取源文件方法

    private Map<K, T> sourceMap;// ID作为KEY 解析后的所有节点
    private Map<K, N> nodeMap;// ID作为KEY 解析后的所有节点
    @Getter
    private Map<K, N> nodeTree;// ID作为KEY 解析后的所有节点


    /**
     * 获取一个解析树对象
     * @param sourceColl 源数据
     * @param getId      获取ID的方法
     * @param getPId     获取PID的方法
     */
    public LTreeProcessor(Collection<T> sourceColl, Function<T, K> getId, Function<T, K> getPId,
                          Function<T, N> oriToNode, Function<N, T> nodeGetOri) {
        this.data = Objects.requireNonNull(sourceColl, "数据源不能为空!");
        this.getId = Objects.requireNonNull(getId, "获取ID的方法不能为空!");
        this.getPId = Objects.requireNonNull(getPId, "获取父ID的方法不能为空!");
        this.oriToNode = Objects.requireNonNull(oriToNode, "源文件转节点方法不能为空!");
        this.nodeGetOri = Objects.requireNonNull(nodeGetOri, "节点获取源文件方法不能为空!");

        int defaultSize = 22;// 子节点个数默认数量

        // 将原数据放入一个Map中
        int capacity = LgrenUtil.capacity(sourceColl.size());
        this.sourceMap = new HashMap<>(capacity);
        this.nodeMap = new HashMap<>(capacity);
        for (T t : sourceColl) {
            this.sourceMap.put(getId.apply(t), t);
            this.nodeMap.put(getId.apply(t), oriToNode.apply(t));
        }

        // 数据ID都不能为空
        this.nodeTree = new LinkedHashMap<>(defaultSize);
        for (T sourceItem : sourceColl) {
            K id = Objects.requireNonNull(getId.apply(sourceItem), "树ID不能为空");// 获取当前节点ID
            N node = this.nodeMap.get(id);// 当前节点
            K pid = getPId.apply(sourceItem);// 获取当前节点父节点

            // 自己是根节点 pid为空 或 自身id和pid相同
            if (pid == null || Objects.equals(id, pid)) {
                this.nodeTree.put(id, node);
            } else {
                N pNode = this.nodeMap.get(pid);
                if (pNode == null) {
                    if (pIdNotFindNode != null) {
                        pIdNotFindNode.accept(pid);
                    }
                    continue;
                }
                pNode.addChild(node);
            }
        }
    }

    /**
     * 获取一个解析树对象
     * @param sourceColl 源数据
     * @param getId      获取ID的方法
     * @param getPId     获取PID的方法
     */
    public static <T, K> LTreeProcessor<T, K, Node<T>> get(Collection<T> sourceColl, Function<T, K> getId, Function<T, K> getPId) {
        return new LTreeProcessor<>(sourceColl, getId, getPId, Node::new, Node::getSource);
    }

    public T get(K id) {
        return sourceMap.get(id);
    }

    public N getNode(K id) {
        return nodeMap.get(id);
    }

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
        // 获取父节点id
        K pid = getPId.apply(source);
        // 获取父节点
        T parentSource;
        // 当父节点不为空
        while ((deep < 1 || deep > nowDeep) && pid != null && !Objects.equals(id, pid) && (parentSource = sourceMap.get(pid)) != null) {
            if ((feature & FEATURE_THIS_DEEP) == 0) {
                result.add(returnConvert.apply(parentSource));
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

    //region 获取子节点
    public List<T> getChildren(K id) {
        return getChildren(id, FEATURE_DEFAULT, DEEP_ALL);
    }

    public List<K> getChildrenId(K id) {
        return getChildrenId(id, FEATURE_DEFAULT, DEEP_ALL);
    }

    public List<N> getChildrenNode(K id) {
        return getChildrenNode(id, FEATURE_DEFAULT, DEEP_ALL);
    }

    public List<T> getChildren(K id, int feature, int deep) {
        return getChildrenBase(id, feature, deep, o -> nodeGetOri.apply(o));
    }

    public List<K> getChildrenId(K id, int feature, int deep) {
        return getChildrenBase(id, feature, deep, o -> getId.apply(nodeGetOri.apply(o)));
    }

    public List<N> getChildrenNode(K id, int feature, int deep) {
        return getChildrenBase(id, feature, deep, o -> o);
    }

    public <R> List<R> getChildrenBase(K id, int feature, int deep, Function<N, R> returnConvert) {
        N node = nodeMap.get(id);
        if (node == null) {
            return Collections.emptyList();
        }
        return getChildrenBaseCallback(new ArrayList<>(22), node.getChildren(), feature, deep, returnConvert, 1);
    }

    private <R, C extends Collection<R>> C getChildrenBaseCallback(C result, Collection<N> nodeColl, int feature, int deep, Function<N, R> returnConvert, int nowDeep) {
        Objects.requireNonNull(result, "结果集不能为空!");
        if ((deep > 0 && nowDeep > deep) || nodeColl == null || nodeColl.isEmpty()) {
            return result;
        }
        for (N node : nodeColl) {
            if ((feature & FEATURE_THIS_DEEP) == 0) {
                result.add(returnConvert.apply(node));
            } else if (deep == nowDeep) {
                result.add(returnConvert.apply(node));
                continue;
            }
            getChildrenBaseCallback(result, node.getChildren(), feature, deep, returnConvert, nowDeep + 1);
        }
        return result;
    }
    //endregion

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
        return treeToListBase(feature, deep, o -> nodeGetOri.apply(o));
    }

    public List<K> treeToIdList(int feature, int deep) {
        return treeToListBase(feature, deep, o -> getId.apply(nodeGetOri.apply(o)));
    }

    public List<N> treeToNodeList(int feature, int deep) {
        return treeToListBase(feature, deep, o -> o);
    }

    private <R> List<R> treeToListBase(int feature, int deep, Function<N, R> returnConvert) {
        List<R> result = new ArrayList<>(22);
        int nowDeep = 1;
        List<Collection<N>> tmpNodeChildrenCollList = new LinkedList<>();
        List<Collection<N>> tmpNodeCollList = new LinkedList<>();
        tmpNodeCollList.add(nodeTree.values());
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
                            Optional.ofNullable(node.getChildren()).filter(c -> !c.isEmpty()).ifPresent(tmpNodeChildrenCollList::add);
                        }
                        // 只需要当前深度 且 未达到当前深度
                    } else {
                        Optional.ofNullable(node.getChildren()).filter(c -> !c.isEmpty()).ifPresent(tmpNodeChildrenCollList::add);
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

    @Data
    public static class Node<T> implements INode<Node<T>> {
        private T source;
        private Collection<Node<T>> children;

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

}
