package com.lgren.design_pattern.thing.commodityImpl;

import com.lgren.design_pattern.thing.Commodity;
import com.lgren.design_pattern.thing.Packing;
import com.lgren.design_pattern.thing.Type;
import com.lgren.design_pattern.thing.attributeImpl.PackingBottle;
import com.lgren.design_pattern.thing.attributeImpl.TypeTool;

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
