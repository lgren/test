package com.lgren.design_pattern.thing;

import com.lgren.design_pattern.thing.commodityImpl.Chocolate;
import com.lgren.design_pattern.thing.commodityImpl.Pen;
import com.lgren.design_pattern.thing.commodityImpl.Pencil;

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
