package com.lgren.design_pattern.strategy.implement1.duck;

import com.lgren.design_pattern.strategy.implement1.attribute.color.Color;
import com.lgren.design_pattern.strategy.implement1.attribute.gender.Gender;
import com.lgren.design_pattern.strategy.implement1.attribute.size.Size;

public interface Duck extends Gender,Color,Size {
    default String display() {
        return String.format("%s%s的%s鸭子", getGender(), getColor(), getSize());
    }

    void eachDifferent();
}
