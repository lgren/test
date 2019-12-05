package com.lgren.design_pattern.strategy.implement1.attribute.size;

public interface SizeBig extends Size {
    @Override
    default String getSize() {
        return "大";
    }
}
