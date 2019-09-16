package com.lgren.design_pattern.thing.attributeImpl;

import com.lgren.design_pattern.thing.Packing;

/**
 * TODO
 *
 * @author Lgren
 * @create 2018-09-28 16:04
 **/
public class PackingWrapper implements Packing {
    @Override
    public String packing() {
        return "纸";
    }
}
