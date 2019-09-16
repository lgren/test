package com.lgren.rxsg;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Lgren
 * @since 2019-05-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CfgTieAttribute {
    private static final long serialVersionUID = 1L;

    private Integer attid;

    private Integer tieid;

    private Integer value;

    private Integer precond;


}
