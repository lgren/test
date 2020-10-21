package com.lgren.process;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

/**
 * TODO
 * @author lgren
 * @since 2020-07-06 5:10 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProcessNodeConfig {
    private Integer id;
    private Integer pId;
    private String name;
    private Integer processConfigId;
    private Integer order;
    private String idPath;
    private String namePath;

    public ProcessNodeConfig addNode(String name) {
        int newId = ProcessService.getId();
        return new ProcessNodeConfig(newId, this.id, name, processConfigId, 0, addPath(idPath, newId), addPath(namePath, name));
    }

    public ProcessNodeConfig addNodeAfter(String name) {
        int newId = ProcessService.getId();
        return new ProcessNodeConfig(newId, pId, name, processConfigId, order + 1, addPath(idPath, newId), addPath(namePath, name));
    }

    public ProcessNodeConfig addNodeBefore(String name) {
        int newId = ProcessService.getId();
        return new ProcessNodeConfig(newId, pId, name, processConfigId, order - 1, addPath(idPath, newId), addPath(namePath, name));
    }

    public static String addPath(String oldPath, Object o) {
        return (oldPath != null ? (oldPath + "/") : "")  + o;
    }
}
