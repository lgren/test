package com.lgren.design_pattern.strategy.implement2;

import com.lgren.design_pattern.strategy.implement2.duck.Duck;
import com.lgren.design_pattern.strategy.implement2.duck.DuckFemaleWhiteMedium;
import com.lgren.design_pattern.strategy.implement2.duck.DuckMaleBlackBig;

/**
 * 策略模式 测试类
 * 此方式使用 类实现属性接口类 然后在使用类中设置属性
 * 优点: 可以运行中直接修改属性
 * 缺点: 写法较为复杂
 * @author lgren
 * @since 2019-12-04 09:40
 */
public class TestMain {    public static void main(String[] args) {
        Duck duck1 = new DuckFemaleWhiteMedium();
        System.out.println(duck1.display());
        Duck duck2 = new DuckMaleBlackBig();
        System.out.println(duck2.display());
    }
}
