package com.lgren.建造者模式.thing;


import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author Lgren
 * @create 2018-09-28 16:15
 **/
public class Order {
    private List<Item> itemList;

    public Order addItem(Item item) {
        if (CollectionUtils.isEmpty(itemList)) {
            itemList = new ArrayList<>(8);
        }
        itemList.add(item);
        return this;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public float getCost() {
        return itemList.stream().map(o -> o.getCommodity().getPrice() * o.getNumber()).reduce((a, b) -> a + b).orElse(-1F);
    }
}
