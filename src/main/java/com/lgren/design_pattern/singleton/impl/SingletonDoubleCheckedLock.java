package com.lgren.design_pattern.singleton.impl;

/**
 * 双重检查加锁（double-checked locking）：只有第一次才进行同步检查，大大减少了getInstance的时间
 * 实现单例：DCL也就是双重锁判断机制（由于JVM底层模型原因，偶尔会出问题，不建议使用）
 * @author lgren
 * @since 2019-09-12 14:32
 */
public class SingletonDoubleCheckedLock {
    private volatile static SingletonDoubleCheckedLock singletonDoubleCheckedLock;

    private SingletonDoubleCheckedLock() { }

    public static SingletonDoubleCheckedLock getInstance() {
        if (singletonDoubleCheckedLock == null) {
            synchronized (SingletonDoubleCheckedLock.class) {
                if (singletonDoubleCheckedLock == null) {
                    singletonDoubleCheckedLock = new SingletonDoubleCheckedLock();
                }
            }
        }
        return singletonDoubleCheckedLock;
    }

    public String method() {
        return "双重检查加锁";
    }
}
