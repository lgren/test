package com.lgren.process;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
 * @author lgren
 * @since 2020-07-06 5:10 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProcessConfig {

    private Integer id;
    private String name;
    public ProcessNodeConfig addNode(String name) {
        int newId = ProcessService.getId();
        return new ProcessNodeConfig(newId, newId, name, this.id, 0, ProcessNodeConfig.addPath(null, newId), ProcessNodeConfig.addPath(null, name));
    }
}
