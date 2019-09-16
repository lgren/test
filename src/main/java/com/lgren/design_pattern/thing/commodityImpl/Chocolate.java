package com.lgren.design_pattern.thing.commodityImpl;

import com.lgren.design_pattern.thing.Commodity;
import com.lgren.design_pattern.thing.Packing;
import com.lgren.design_pattern.thing.Type;
import com.lgren.design_pattern.thing.attributeImpl.PackingWrapper;
import com.lgren.design_pattern.thing.attributeImpl.TypeFood;

/**
 * TODO
 *
 * @author Lgren
 * @create 2018-09-28 16:13
 **/
public class Chocolate extends Commodity {
    private Type type;
    private Packing packing;
    private String name;
    private float price;
    @Override
    public Type getType() {
        return new TypeFood();
    }

    @Override
    public Packing getPacking() {
        return new PackingWrapper();
    }

    @Override
    public String getName() {
        return "巧克力";
    }

    @Override
    public float getPrice() {
        return 8.00F;
    }
}
