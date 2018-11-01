package com.lgren.建造者模式.thing.commodityImpl;

import com.lgren.建造者模式.thing.Commodity;
import com.lgren.建造者模式.thing.Packing;
import com.lgren.建造者模式.thing.Type;
import com.lgren.建造者模式.thing.attributeImpl.PackingWrapper;
import com.lgren.建造者模式.thing.attributeImpl.TypeFood;

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
