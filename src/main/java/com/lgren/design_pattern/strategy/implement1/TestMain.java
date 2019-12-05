package com.lgren.design_pattern.strategy.implement1;

import com.lgren.design_pattern.strategy.implement1.duck.Duck;
import com.lgren.design_pattern.strategy.implement1.duck.DuckFemaleRedSmall;
import com.lgren.design_pattern.strategy.implement1.duck.DuckMaleBlackBig;

/**
 * 策略模式 测试类
 * 此方式使用java8 interface的default
 * 优点: 写法很简单
 * 缺点: 定义类的时候已经写死了属性 不能更改
 * @author lgren
 * @since 2019-12-04 09:40
 */
public class TestMain {
    public static void main(String[] args) {
        Duck duck1 = new DuckFemaleRedSmall();
        System.out.println(duck1.display());
        Duck duck2 = new DuckMaleBlackBig();
        System.out.println(duck2.display());
    }
}
