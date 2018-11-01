package com.lgren.建造者模式.thing.commodityImpl;

import com.lgren.建造者模式.thing.Commodity;
import com.lgren.建造者模式.thing.Packing;
import com.lgren.建造者模式.thing.Type;
import com.lgren.建造者模式.thing.attributeImpl.PackingBottle;
import com.lgren.建造者模式.thing.attributeImpl.TypeTool;

/**
 * TODO
 *
 * @author Lgren
 * @create 2018-09-28 16:09
 **/
public class Pencil extends Commodity {
    @Override
    public Type getType() {
        return new TypeTool();
    }

    @Override
    public Packing getPacking() {
        return new PackingBottle();
    }

    @Override
    public String getName() {
        return "铅笔";
    }

    @Override
    public float getPrice() {
        return 5.35F;
    }
}
