package com.lgren.rxsg;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * TODO
 * @author lgren
 * @create 2019-06-05 09:49
 **/
public class CfgTieTest {
    static List<CfgTieAttribute> list = new ArrayList<>(10);
    static {
        list.add(new CfgTieAttribute().setTieid(1).setAttid(1).setPrecond(1).setValue(10));
        list.add(new CfgTieAttribute().setTieid(2).setAttid(2).setPrecond(2).setValue(10));
        list.add(new CfgTieAttribute().setTieid(3).setAttid(3).setPrecond(3).setValue(10));
        list.add(new CfgTieAttribute().setTieid(4).setAttid(4).setPrecond(4).setValue(10));
        list.add(new CfgTieAttribute().setTieid(5).setAttid(5).setPrecond(5).setValue(10));
        list.add(new CfgTieAttribute().setTieid(6).setAttid(6).setPrecond(6).setValue(10));
        list.add(new CfgTieAttribute().setTieid(7).setAttid(7).setPrecond(7).setValue(10));
        list.add(new CfgTieAttribute().setTieid(8).setAttid(8).setPrecond(8).setValue(10));
        list.add(new CfgTieAttribute().setTieid(9).setAttid(9).setPrecond(9).setValue(10));
        list.add(new CfgTieAttribute().setTieid(10).setAttid(10).setPrecond(10).setValue(10));
    }

    static List<CfgTieAttribute> list1 = Lists.newArrayList(list.get(0), list.get(1), list.get(2), list.get(3), list.get(4));
    static List<CfgTieAttribute> list2 = Lists.newArrayList(list.get(2), list.get(3), list.get(4), list.get(5), list.get(6));

    @Test
    public void intersection_minus_union() {
    }

    public <T> void editSubList(List<T> oldList, List<T> newList, Function<T, ?> judge, Consumer<T> update, Consumer<T> insert, Consumer<T> delete) {
        oldList.forEach(o -> {

        });


        newList.forEach(o -> {
            if (oldList.contains(o)) {
                // 修改
                oldList.remove(o);
            } else {
                // 新增
            }
        });
        oldList.forEach(o -> {
            // 删除
        });
    }
}
