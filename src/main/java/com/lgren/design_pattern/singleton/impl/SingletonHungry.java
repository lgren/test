package com.lgren.design_pattern.singleton.impl;

/**
 * 饿汉模式
 * 描述：这种方式比较常用，但容易产生垃圾对象。
 * 优点：没有加锁，执行效率会提高。
 * 缺点：类加载时就初始化，浪费内存。
 * @author lgren
 * @since 2019-12-12 10:51
 */
public class SingletonHungry {
    private static SingletonHungry singletonHungry = new SingletonHungry();

    private SingletonHungry() {
        System.out.println("12345");
    }

    public static SingletonHungry getInstance() {
        return singletonHungry;
    }

    public String method() {
        return "饿汉模式";
    }

}
