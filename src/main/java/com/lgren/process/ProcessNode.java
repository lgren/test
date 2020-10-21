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
@AllArgsConstructor
@Builder
public class ProcessNode {
    private Integer id;
    private Integer pId;
    private Integer processId;
    private Integer order;
}
