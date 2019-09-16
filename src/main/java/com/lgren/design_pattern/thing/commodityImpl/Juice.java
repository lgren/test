package com.lgren.design_pattern.thing.commodityImpl;

import com.lgren.design_pattern.thing.Commodity;
import com.lgren.design_pattern.thing.Packing;
import com.lgren.design_pattern.thing.Type;
import com.lgren.design_pattern.thing.attributeImpl.PackingBottle;
import com.lgren.design_pattern.thing.attributeImpl.TypeFood;

/**
 * TODO
 *
 * @author Lgren
 * @create 2018-09-28 16:11
 **/
public class Juice extends Commodity {
    @Override
    public Type getType() {
        return new TypeFood();
    }

    @Override
    public Packing getPacking() {
        return new PackingBottle();
    }

    @Override
    public String getName() {
        return "饮料";
    }

    @Override
    public float getPrice() {
        return 3.00F;
    }
}
