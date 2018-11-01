package com.lgren.建造者模式.thing.attributeImpl;

import com.lgren.建造者模式.thing.Packing;

/**
 * TODO
 *
 * @author Lgren
 * @create 2018-09-28 16:04
 **/
public class PackingBottle implements Packing {
    @Override
    public String packing() {
        return "玻璃";
    }
}
