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
public class Process {
    private Integer id;
    private Integer processConfigId;
}
