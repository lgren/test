package com.lgren.design_pattern.strategy.implement2.duck;

import com.lgren.design_pattern.strategy.implement2.attribute.color.Color;
import com.lgren.design_pattern.strategy.implement2.attribute.gender.Gender;
import com.lgren.design_pattern.strategy.implement2.attribute.size.Size;

public abstract class Duck {
    protected Color color;
    protected Gender gender;
    protected Size size;

    public String display() {
        return String.format("%s%s的%s鸭子", gender.getGender(), color.getColor(), size.getSize());
    }

    public abstract void eachDifferent();

}
