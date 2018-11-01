package com.lgren.建造者模式.thing.commodityImpl;

import com.lgren.建造者模式.thing.Commodity;
import com.lgren.建造者模式.thing.Packing;
import com.lgren.建造者模式.thing.Type;
import com.lgren.建造者模式.thing.attributeImpl.PackingBottle;
import com.lgren.建造者模式.thing.attributeImpl.TypeFood;

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
