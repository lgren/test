package com.lgren.jyyh_jsp.base;


import com.lgren.jyyh_jsp.tag.IsNotAttr;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * TODO
 * @create 2019-06-21 17:54
 * @since lgren
 */
public interface WithChildren<This extends WithChildren, T> {
    // private List<This> children;

    @IsNotAttr
    List<T> getChildren();

    @IsNotAttr
    This setChildren(List<T> children);

    @SuppressWarnings("unchecked")
    @IsNotAttr
    default This addChild(T... children) {
        if (ArrayUtils.isNotEmpty(children)) {
            List<T> children1 = getChildren();
            if (children1 == null) {
                children1 = new ArrayList<>();
                setChildren(children1);
            }
            for (T base : children) {
                Optional.ofNullable(base).ifPresent(children1::add);
            }
        }
        return (This) this;
    }}
