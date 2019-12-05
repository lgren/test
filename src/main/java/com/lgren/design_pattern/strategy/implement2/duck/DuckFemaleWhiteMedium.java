package com.lgren.design_pattern.strategy.implement2.duck;

import com.lgren.design_pattern.strategy.implement2.attribute.color.ColorWhite;
import com.lgren.design_pattern.strategy.implement2.attribute.gender.GenderFemale;
import com.lgren.design_pattern.strategy.implement2.attribute.size.SizeMedium;

public class DuckFemaleWhiteMedium extends Duck {

    public DuckFemaleWhiteMedium() {
        this.gender = new GenderFemale();
        this.color = new ColorWhite();
        this.size = new SizeMedium();
    }

    @Override
    public void eachDifferent() {
        System.out.println("自己定义不同");
    }
}
