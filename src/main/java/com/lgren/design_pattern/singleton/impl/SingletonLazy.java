package com.lgren.design_pattern.singleton.impl;

/**
 * 线程安全懒汉模式
 * 描述：这种方式具备很好的 lazy loading，能够在多线程中很好的工作，但是，效率很低，99% 情况下不需要同步。
 * 优点：第一次调用才初始化，避免内存浪费。
 * 缺点：必须加锁 synchronized 才能保证单例，但加锁会影响效率。
 * @author lgren
 * @since 2019-12-12 10:49
 */
public class SingletonLazy {
    private static SingletonLazy singletonLazy;

    private SingletonLazy() {}

    public static synchronized SingletonLazy getInstance() {
        if (singletonLazy == null) {
            singletonLazy = new SingletonLazy();
        }
        return singletonLazy;
    }

    public String method() {
        return "线程安全懒汉模式";
    }
}
