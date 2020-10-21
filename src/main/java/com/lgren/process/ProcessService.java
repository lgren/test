package com.lgren.process;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * TODO
 * @author lgren
 * @since 2020-07-06 5:15 下午
 */
public class ProcessService {
    public void start(ProcessConfig config) {
        Process process = insert(new Process(ProcessService.getId(), config.getId()));
        List<ProcessNodeConfig> nodeConfigList = queryNodeConfig(ProcessNodeConfig.builder().processConfigId(process.getProcessConfigId()).build(), o -> Objects.equals(o.getId(), o.getPId()));

    }

    public static void main(String[] args) {
        ProcessService processService = new ProcessService();

        ProcessConfig processConfig = processService.insert(new ProcessConfig(ProcessService.getId(), "流程设置1"));
        ProcessNodeConfig node1 = processService.insert(processConfig.addNode("节点1"));
        ProcessNodeConfig node2 = processService.insert(node1.addNode("节点2"));
        ProcessNodeConfig node3 = processService.insert(node2.addNode("节点3"));

        processService.start(processConfig);
    }

    // 模拟 获取ID方法
    private static int id = 1;
    public static int getId() {
        return id++;
    }
    // 模拟 数据库
    private static List<ProcessConfig> processConfigList = new ArrayList<>();
    private static List<ProcessNodeConfig> processNodeConfigList = new ArrayList<>();
    private static List<Process> processList = new ArrayList<>();
    private static List<ProcessNode> processNodeList = new ArrayList<>();
    // 模拟 插入语句
    public <T> T insert(T t) {
        if (t instanceof ProcessConfig) {
            processConfigList.add((ProcessConfig) t);
        } else if (t instanceof ProcessNodeConfig) {
            processNodeConfigList.add((ProcessNodeConfig) t);
        } else if (t instanceof Process) {
            processList.add((Process) t);
        } else if (t instanceof ProcessNode) {
            processNodeList.add((ProcessNode) t);
        }
        return t;
    }
    // 模拟 查询语句
    public List<ProcessNodeConfig> queryNodeConfig(ProcessNodeConfig processNodeConfig, Predicate<ProcessNodeConfig>... otherFunc) {
        Stream<ProcessNodeConfig> stream = processNodeConfigList.stream()
                .filter(n -> Optional.ofNullable(processNodeConfig.getProcessConfigId()).filter(Objects::nonNull).map(o -> Objects.equals(o, n.getProcessConfigId())).orElse(true))
                .filter(n -> Optional.ofNullable(processNodeConfig.getPId()).filter(Objects::nonNull).map(o -> Objects.equals(o, n.getPId())).orElse(true));
        for (Predicate<ProcessNodeConfig> other : otherFunc) {
            if (other != null) {
                stream = stream.filter(other);
            }
        }
        return stream.sorted((p, n) -> n.getOrder() - p.getOrder()).collect(Collectors.toList());
    }
}
