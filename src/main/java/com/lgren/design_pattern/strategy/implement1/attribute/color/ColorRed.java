package com.lgren.design_pattern.strategy.implement1.attribute.color;

public interface ColorRed extends Color {
    @Override
    default String getColor() {
        return "红色";
    }
}
