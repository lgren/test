package com.lgren.util;

import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.ss.formula.functions.T;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 树的工具类
 * @author lgren
 * @since 2019-09-16 13:57
 */
public class LTreeUtil {
    /**
     * 读取树结构 参考zTree源码 会对原有数据产生影响 会将data中的数据添加一个childrenField字段
     * @param data          数据源
     * @param idField       节点ID字段名
     * @param pidField      父节点ID字段名
     * @param childrenField 子节点列表字段名
     * @param excludeIds    需要排除的节点ID数组
     * @return 通过data生成的树
     * @author Lgren
     * @since 2019/9/16 17:09
     */
    public static List<Map<String, Object>> listToTree(List<Map<String, Object>> data,
                                                       String idField, String pidField, String childrenField,
                                                       Object... excludeIds) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        Set<Object> excludeIdSet = Optional.ofNullable(excludeIds).filter(o -> o.length != 0).map(Sets::newHashSet).orElse(null);
        // 将原数据放入一个Map中
        Map<Object, Map<String, Object>> tmpMap = data.stream().collect(Collectors.toMap(m -> m.get(idField), m -> m, (oldMap, newMap) -> oldMap));
        for (Map<String, Object> node : data) {
            Object id = node.get(idField);// 获取当前节点ID
            Object pid = node.get(pidField);// 获取当前节点父节点
            // 如果当前节点属于被排除节点 则跳过
            if (CollectionUtils.isNotEmpty(excludeIdSet) && excludeIdSet.contains(id)) {
                continue;
            }
            Map<String, Object> pNode = tmpMap.get(pid);// 获取父节点
            //tmpMap存储的均为id为key的键值对，如果以pid为key可以取出对象，则表明该元素是父级元素
            if (pNode != null && !Objects.equals(id, pid)) {
                //给当前这个父级map对象中添加key为children的ArrayList
                List<Map<String, Object>> children = (List<Map<String, Object>>) pNode.computeIfAbsent(childrenField, k -> new ArrayList<>());
                children.add(node);
            } else {// 没有父节点或者自己ID等于父ID则为根节点
                resultList.add(node);
            }
        }
        return resultList;
    }

    /**
     * 读取树结构 参考zTree源码 会对原有数据产生影响 会将data中的数据添加一个childrenField字段
     * @param data       数据源
     * @param getId      节点ID字段名
     * @param getPId     父节点ID字段名
     * @param excludeIds 需要排除的节点ID数组
     * @return 通过data生成的树
     * @author Lgren
     * @since 2019/9/16 17:09
     */
    public static <T extends WithChildren> List<T> listToTree(List<T> data,
                                                              Function<T, ?> getId, Function<T, ?> getPId,
                                                              Object... excludeIds) {
        List<T> resultList = new ArrayList<>();
        Set<Object> excludeIdSet = Optional.ofNullable(excludeIds).filter(o -> o.length != 0).map(Sets::newHashSet).orElse(null);
        // 将原数据放入一个Map中
        Map<Object, T> tmpMap = data.stream().collect(Collectors.toMap(getId::apply, m -> m, (oldMap, newMap) -> oldMap));
        for (T node : data) {
            Object id = getId.apply(node);// 获取当前节点ID
            Object pid = getPId.apply(node);// 获取当前节点父节点
            // 如果当前节点属于被排除节点 则跳过
            if (CollectionUtils.isNotEmpty(excludeIdSet) && excludeIdSet.contains(id)) {
                continue;
            }
            T pNode = tmpMap.get(pid);// 获取父节点
            //tmpMap存储的均为id为key的键值对，如果以pid为key可以取出对象，则表明该元素是父级元素
            if (pNode != null && !Objects.equals(id, pid)) {
                if (pNode.getChildren() == null) {
                    pNode.setChildren(new ArrayList<>());
                }
                //给当前这个父级map对象中添加key为children的ArrayList
                Collection<WithChildren> children = pNode.getChildren();
                children.add(node);
            } else {// 没有父节点或者自己ID等于父ID则为根节点
                resultList.add(node);
            }
        }
        return resultList;
    }

    /**
     * 读取树结构 参考zTree源码 会对原有数据产生影响 会将data中的数据添加一个childrenField字段
     * @param data          数据源
     * @param idField       节点ID字段名
     * @param pidField      父节点ID字段名
     * @param childrenField 子节点列表字段名
     * @param excludeIds    需要排除的节点ID数组
     * @return 通过data生成的树
     * @author Lgren
     * @since 2019/9/16 17:09
     */
    public static Map<Object, Map<String, Object>> listToTreeMap(List<Map<String, Object>> data,
                                                                 String idField, String pidField, String childrenField,
                                                                 Object... excludeIds) {
        Map<Object, Map<String, Object>> resultMap = new HashMap<>();
        Set<Object> excludeIdSet = Optional.ofNullable(excludeIds).filter(o -> o.length != 0).map(Sets::newHashSet).orElse(null);
        // 将原数据放入一个Map中
        Map<Object, Map<String, Object>> tmpMap = data.stream().collect(Collectors.toMap(m -> m.get(idField), m -> m, (oldMap, newMap) -> oldMap));
        for (Map<String, Object> node : data) {
            Object id = node.get(idField);// 获取当前节点ID
            Object pid = node.get(pidField);// 获取当前节点父节点
            // 如果当前节点属于被排除节点 则跳过
            if (CollectionUtils.isNotEmpty(excludeIdSet) && excludeIdSet.contains(id)) {
                continue;
            }
            Map<String, Object> pNode = tmpMap.get(pid);// 获取父节点
            //tmpMap存储的均为id为key的键值对，如果以pid为key可以取出对象，则表明该元素是父级元素
            if (pNode != null && !Objects.equals(id, pid)) {
                //给当前这个父级map对象中添加key为children的HashMap
                Map<Object, Map<String, Object>> childrenMap = (Map<Object, Map<String, Object>>) pNode.computeIfAbsent(childrenField, k -> new HashMap<>());
                childrenMap.put(id, node);
            } else {// 没有父节点或者自己ID等于父ID则为根节点
                resultMap.put(id, node);
            }
        }
        return resultMap;
    }

    /**
     * 过滤掉树节点以及其下面的子节点 会对原有数据产生影响 会将data中的部分数据添加一个childrenField字段 返回结果的数据不会有此字段
     * @param data          数据源
     * @param idField       节点ID字段名
     * @param pidField      父节点ID字段名
     * @param childrenField 子节点列表字段名
     * @param excludeIds    需要排除的节点ID数组
     * @return 过滤后的list
     * @author Lgren
     * @since 2019/9/16 17:09
     */
    public static List<Map<String, Object>> filterList(List<Map<String, Object>> data,
                                                       String idField, String pidField, String childrenField,
                                                       Object... excludeIds) {
        // 1.排除部分值 然后将所有的数据转化为树 如果父节点不存在 则子节点也不存在 所以达到移除节点以及其子节点的目的
        List<Map<String, Object>> tree = listToTree(data, idField, pidField, childrenField, excludeIds);
        // 2.将树转化为list
        List<Map<String, Object>> result = new ArrayList<>(data.size());
        // 递归将树转为list
        treeToList(result, tree, childrenField);
        return result;
    }

    /**
     * 获取自身节点以及其所有父节点 会对原有数据产生影响 会将data中的部分数据添加一个parentField字段 返回结果的数据不会有此字段
     * @param data        数据源
     * @param id          需要获取的节点的id名
     * @param idField     节点ID字段名
     * @param pidField    父节点ID字段名
     * @param parentField 父节点字段名
     * @return 返回自身节点以及其所有父节点
     * @author Lgren
     * @since 2019/9/16 17:09
     */
    public static List<Map<String, Object>> getParents(List<Map<String, Object>> data, Object id,
                                                       String idField, String pidField, String parentField) {
        //region 1.将数据源所有的节点添加节点parentField
        // 将原数据放入一个Map中
        Map<Object, Map<String, Object>> tmpMap = data.stream().collect(Collectors.toMap(m -> m.get(idField), m -> m, (oldMap, newMap) -> oldMap));
        for (Map<String, Object> node : data) {
            Object pid = node.get(pidField);// 获取当前节点父节点
            Map<String, Object> pNode = tmpMap.get(pid);// 获取父节点
            // 父节点为空 或者 当前节点与父节点ID相同
            if (pNode == null || Objects.equals(node.get(idField), pid)) {
                continue;
            }
            node.put(parentField, pNode);
        }
        //endregion

        //region 2.找到需要寻找的节点
        for (Map<String, Object> node : data) {
            if (Objects.equals(node.get(idField), id)) {
                List<Map<String, Object>> result = new ArrayList<>();
                Map<String, Object> nodeTemp = node;
                // 循环自身节点添加入结果集 并让temp赋值为新的当前节点
                while (nodeTemp != null) {
                    // 将此节点添加进结果list
                    result.add(nodeTemp);
                    // 去掉父节点字段并将父节点进行下一步循环
                    nodeTemp = (Map<String, Object>) nodeTemp.remove(parentField);
                }
                return result;
            }
        }
        //endregion
        return new ArrayList<>(0);
    }

    /**
     * 将树转为list 会对原有数据产生影响 每个对象中可能减少一个childrenField字段
     * @param result        结果数据 用于收集数据递归
     * @param tree          树的数据源
     * @param childrenField 子节点列表字段名
     * @author Lgren
     * @since 2019/9/16 17:22
     */
    public static void treeToList(List<Map<String, Object>> result, List<Map<String, Object>> tree, String childrenField) {
        // 如果结果列表为空 或 树的数据源为空 则不做任何处理
        if (result == null || tree == null || tree.isEmpty()) {
            return;
        }
        // 将树的当前层添加进结果
        result.addAll(tree);
        for (Map<String, Object> node : tree) {
            // 获取当前层的节点的子节点列表 并移除
            List<Map<String, Object>> children = (List<Map<String, Object>>) node.remove(childrenField);
            // 递归 将子树添加进结果集中
            treeToList(result, children, childrenField);
        }
    }

    /**
     * 将树转为list 会对原有数据产生影响 每个对象中可能减少一个childrenField字段
     * @param result        结果数据 用于收集数据递归
     * @param tree          树的数据源
     * @param childrenField 子节点列表字段名
     * @author Lgren
     * @since 2019/9/16 17:22
     */
    public static void treeToList(List<Map<String, Object>> result, List<Map<String, Object>> tree, String childrenField,
                                  BiConsumer<Map<String, Object>, List<Map<String, Object>>> pNodeAndChildrenConsumer) {
        // 如果结果列表为空 或 树的数据源为空 则不做任何处理
        if (result == null || tree == null || tree.isEmpty()) {
            return;
        }
        // 将树的当前层添加进结果
        result.addAll(tree);
        for (Map<String, Object> node : tree) {
            // 获取当前层的节点的子节点列表 并移除
            List<Map<String, Object>> children = (List<Map<String, Object>>) node.remove(childrenField);
            // 递归 将子树添加进结果集中
            treeToList(result, children, childrenField, pNodeAndChildrenConsumer);
            if (children != null && !children.isEmpty()) {
                pNodeAndChildrenConsumer.accept(node, children);
            }
        }
    }

    public static class WithChildren {
        private Collection<WithChildren> children;

        public Collection<WithChildren> getChildren() {
            return children;
        }

        public void setChildren(Collection<WithChildren> children) {
            this.children = children;
        }
    }
}
