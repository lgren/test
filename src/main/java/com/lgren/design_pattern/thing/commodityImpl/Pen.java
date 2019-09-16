package com.lgren.design_pattern.thing.commodityImpl;

import com.lgren.design_pattern.thing.Commodity;
import com.lgren.design_pattern.thing.Packing;
import com.lgren.design_pattern.thing.Type;
import com.lgren.design_pattern.thing.attributeImpl.PackingWrapper;
import com.lgren.design_pattern.thing.attributeImpl.TypeTool;

/**
 * TODO
 * @author Lgren
 * @create 2018-09-28 16:07
 **/
public class Pen extends Commodity {
    @Override
    public Type getType() {
        return new TypeTool();
    }

    @Override
    public Packing getPacking() {
        return new PackingWrapper();
    }

    @Override
    public String getName() {
        return "钢笔";
    }

    @Override
    public float getPrice() {
        return 10.45F;
    }
}
