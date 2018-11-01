package com.lgren.建造者模式.thing.attributeImpl;

import com.lgren.建造者模式.thing.Type;

/**
 * TODO
 *
 * @author Lgren
 * @create 2018-09-28 15:59
 **/
public class TypeTool implements Type {
    private String type;
    @Override
    public String getType() {
        return "工具";
    }
}
