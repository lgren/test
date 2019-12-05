package com.lgren.design_pattern.strategy.implement2.duck;

import com.lgren.design_pattern.strategy.implement2.attribute.color.ColorBlack;
import com.lgren.design_pattern.strategy.implement2.attribute.gender.GenderMale;
import com.lgren.design_pattern.strategy.implement2.attribute.size.SizeBig;

public class DuckMaleBlackBig extends Duck {
    public DuckMaleBlackBig() {
        this.gender = new GenderMale();
        this.color = new ColorBlack();
        this.size = new SizeBig();
    }

    @Override
    public void eachDifferent() {
        System.out.println("自己定义不同");
    }
}
