package com.lgren.design_pattern.strategy.implement1.attribute.color;

public interface ColorWhite extends Color {
    @Override
    default String getColor() {
        return "白色";
    }
}
