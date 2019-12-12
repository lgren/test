package com.lgren.design_pattern.singleton;

import com.lgren.design_pattern.singleton.impl.*;
import org.junit.Test;

/**
 * 单例模式测试类
 * -单例对象 占用资源少，不需要延时加载，枚举 好于 饿汉 {@link com.lgren.design_pattern.singleton.impl.SingletonEnum}
 * -单例对象 占用资源多，需要延时加载，静态内部类 好于 懒汉式{@link com.lgren.design_pattern.singleton.impl.SingletonStaticInner}
 * @author lgren
 * @since 2019-12-12 11:01
 */
public class TestMain {
    /** 枚举方式实现单例(非懒加载推荐方式) */
    @Test
    public void test1() {
        System.out.println(SingletonEnum.INSTANCE.method());
    }

    /** 登记式/静态内部类(懒加载推荐方式) */
    @Test
    public void test2() {
        System.out.println(SingletonStaticInner.getInstance().method());
    }

    /** 双重检查加锁 */
    @Test
    public void test3() {
        System.out.println(SingletonDoubleCheckedLock.getInstance().method());
    }

    /** 饿汉模式 */
    @Test
    public void test4() {
        System.out.println(SingletonHungry.getInstance().method());
    }

    /** 线程安全懒汉模式 */
    @Test
    public void test5() {
        System.out.println(SingletonLazy.getInstance().method());
    }
}
