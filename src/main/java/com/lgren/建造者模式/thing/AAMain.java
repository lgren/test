package com.lgren.建造者模式.thing;

import com.alibaba.fastjson.JSON;
import com.lgren.建造者模式.thing.commodityImpl.Chocolate;
import com.lgren.建造者模式.thing.commodityImpl.Pen;
import com.lgren.建造者模式.thing.commodityImpl.Pencil;

/**
 * TODO
 *
 * @author Lgren
 * @create 2018-09-28 16:27
 **/
public class AAMain {
    public static void main(String[] args) {
        Order order = new Order();
        order.addItem(new Item(new Pencil(), 4))
                .addItem(new Item(new Pen(), 1))
                .addItem(new Item(new Chocolate(), 2));
        System.out.println(order.getCost());


        Pen pen = new Pen();
        System.out.println(pen.getName());

//        Item item = new Item(new Pen(), 1);
//        System.out.println(item);
//        System.out.println(JSON.toJSONString(order.getItemList()));
    }
}
