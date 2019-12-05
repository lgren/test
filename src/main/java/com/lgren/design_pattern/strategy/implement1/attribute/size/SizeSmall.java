package com.lgren.design_pattern.strategy.implement1.attribute.size;

public interface SizeSmall extends Size {
    @Override
    default String getSize() {
        return "小";
    }
}
