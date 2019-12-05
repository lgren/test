package com.lgren.design_pattern.strategy.implement1.duck;

import com.lgren.design_pattern.strategy.implement1.attribute.color.ColorBlack;
import com.lgren.design_pattern.strategy.implement1.attribute.gender.GenderMale;
import com.lgren.design_pattern.strategy.implement1.attribute.size.SizeBig;

public class DuckMaleBlackBig implements Duck, GenderMale, ColorBlack, SizeBig {
    @Override
    public void eachDifferent() {
        System.out.println("自己定义不同");
    }
}
