package com.lgren.design_pattern.strategy.implement1.duck;

import com.lgren.design_pattern.strategy.implement1.attribute.color.ColorRed;
import com.lgren.design_pattern.strategy.implement1.attribute.gender.GenderFemale;
import com.lgren.design_pattern.strategy.implement1.attribute.size.SizeSmall;

public class DuckFemaleRedSmall implements Duck, GenderFemale, ColorRed, SizeSmall {
    @Override
    public void eachDifferent() {
        System.out.println("自己定义不同");
    }
}
