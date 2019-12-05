package com.lgren.design_pattern.strategy.implement1.attribute.color;


public interface ColorBlack extends Color {
    @Override
    default String getColor() {
        return "黑色";
    }
}
